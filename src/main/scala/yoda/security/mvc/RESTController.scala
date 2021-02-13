/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import play.api.libs.Files.TemporaryFile
import play.api.mvc._
import yoda.security.mvc.internal.{Header, ManageAction, StandardComponents}
import yoda.security.mvc.compoments.Json


/**
 * @author Peerapat A on Mar 26, 2019
 */
trait RESTController extends BaseController
  with Header
  with LazyLogging {

  @Inject
  private var json: Json = _

  @Inject
  private var cc: StandardComponents = _

  override def controllerComponents: ControllerComponents = cc

  protected def upload: ManageAction = cc.manageAction

  protected def action = cc.manageAction(cc.parsers.utf8Decoder)

  protected def okJSon[A](implicit ar: AccountRequest[A]): Result = okJSon(JSResponse())

  protected def okJSon[A](m: AnyRef)
                         (implicit ar: AccountRequest[A]): Result = m match {
    case _: String =>
      Ok(m.toString).withHeaders(headers(ar.processTime): _*) as JSON
    case _ =>
      Ok(json.toJson(m)).withHeaders(headers(ar.processTime): _*) as JSON
  }

  implicit class HTTPRequest(request: Request[String]) {

    def toOption[T: Manifest]: Option[T] = {
      logger.debug(s"${request.method} ${request.uri} - ${request.body}")
      json.toOption[T](request.body)
    }

  }

  implicit class FormDataWrapper(formData: MultipartFormData[TemporaryFile]) {

    def asInt(key: String): Option[Int] = formData.dataParts.get(key).flatMap(_.headOption).map(_.toInt)

    def asLong(key: String): Option[Long] = formData.dataParts.get(key).flatMap(_.headOption).map(_.toLong)

    def asString(key: String): Option[String] = formData.dataParts.get(key).flatMap(_.headOption)

  }

}
