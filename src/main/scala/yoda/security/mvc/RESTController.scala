/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.Files.TemporaryFile
import play.api.mvc._
import yoda.security.definitions.Ref
import yoda.security.mvc.compoments.Json
import yoda.security.mvc.internal.{Header, ManageAction, StandardComponents}

import javax.inject.Inject


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

  protected def action: ActionBuilder[AccountRequest, String] = cc.manageAction(cc.parsers.utf8Decoder)

  protected def okJSon(refNo: String)
                      (implicit ar: AccountRequest[String]): Result = {
    logger.info(s"${ar.method} ${ar.path}: $refNo -> 0")
    okJSon(JSResponse(), refNo)
  }

  protected def okJSon[T](js: JSResponse[T], refNo: String)
                      (implicit ar: AccountRequest[String]): Result = {
    logger.info(s"${ar.method} ${ar.path}: $refNo -> ${js.code}")
    Ok(json.toJson(js)).withHeaders(headers(ar.processTime): _*) as JSON
  }

  protected def okJSon(m: AnyRef, refNo: String)
                       (implicit ar: AccountRequest[String]): Result = m match {
    case _: String =>
      logger.info(s"${ar.method} ${ar.path}: $refNo -> 0")
      Ok(m.toString).withHeaders(headers(ar.processTime): _*) as JSON
    case m: AnyRef =>
      logger.info(s"${ar.method} ${ar.path}: $refNo -> 0")
      Ok(json.toJson(m)).withHeaders(headers(ar.processTime): _*) as JSON
  }

  protected def okJSon2(m: AnyRef, refNo: String)
                      (implicit ar: AccountRequest[AnyContent]): Result = m match {
    case _: String =>
      logger.info(s"${ar.method} ${ar.path}: $refNo -> 0")
      Ok(m.toString).withHeaders(headers(ar.processTime): _*) as JSON
    case m: AnyRef =>
      logger.info(s"${ar.method} ${ar.path}: $refNo -> 0")
      Ok(json.toJson(m)).withHeaders(headers(ar.processTime): _*) as JSON
  }

  def withForm[T](ref: Ref[T], r: Request[ByteString]): Option[T] = {
    val body = r.body.decodeString("UTF-8")
    if (r.headers.get("X-Trace-Body").contains("false"))
      logger.trace(s"URI: ${r.uri}\n$body")

    json.toOption(body, ref)
  }

  implicit class HTTPRequest(r: Request[String]) {

    def toOption[T: Manifest]: Option[T] = {
      if (r.headers.get("X-Trace-Body").contains("false"))
        logger.debug(s"${r.method} ${r.uri} - ${r.body}")

      json.toOption[T](r.body)
    }

  }

  implicit class FormDataWrapper(formData: MultipartFormData[TemporaryFile]) {

    def asInt(key: String): Option[Int] = formData.dataParts.get(key).flatMap(_.headOption).map(_.toInt)

    def asLong(key: String): Option[Long] = formData.dataParts.get(key).flatMap(_.headOption).map(_.toLong)

    def asString(key: String): Option[String] = formData.dataParts.get(key).flatMap(_.headOption)

  }

}
