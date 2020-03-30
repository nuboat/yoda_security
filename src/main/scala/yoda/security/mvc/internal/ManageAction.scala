/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.internal

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import play.api.http.{HttpVerbs, MimeTypes}
import play.api.mvc._
import yoda.security.mvc.authorize.{Authorizer, HTTPPermission}
import yoda.security.mvc.{AccountRequest, HiddenException, KnownException}

import scala.concurrent.{ExecutionContext, Future}

/**
 * @author Peerapat A on Mar 26, 2019
 */
private[mvc] class ManageAction @Inject()(manager: Authorizer
                                          , val parser: BodyParsers.Default)
                                         (implicit protected val executionContext: ExecutionContext)
  extends ActionBuilder[AccountRequest, AnyContent]
    with HttpVerbs
    with MimeTypes
    with Header
    with LazyLogging {

  override def invokeBlock[A](request: Request[A],
                              block: AccountRequest[A] => Future[Result]): Future[Result] = {
    implicit val stopwatch: Stopwatch = Stopwatch.createStarted

    val reqID = request.headers.get("X-Req-UUID").getOrElse("NA")
    val access = lookupToken(request)

    try {
      val permission = HTTPPermission(request.method, request.uri)

      val account = manager.lookup(access.orNull)

      val hasAccess = manager.hasAccess(permission = permission, account = account)

      if (!hasAccess)
        throw new IllegalAccessException(s"No authorize to access ${permission.method}: ${permission.action}")

      block(AccountRequest(request = request
        , optAccount = account
        , reqUUID = reqID))

    } catch {
      case e: IllegalAccessException =>
        unauthorized(e).map(_.withHeaders(headers(processTime): _*) as JSON)

      case e: HiddenException =>
        failed(e).map(_.withHeaders(headers(processTime): _*) as JSON)

      case e: KnownException =>
        failed(e).map(_.withHeaders(headers(processTime): _*) as JSON)

      case e: Throwable =>
        internalservererror(e).map(_.withHeaders(headers(processTime): _*) as JSON)

    } finally {
      val userAgent = request.headers.get("User-Agent").getOrElse("N/A")
      logger.info(s"${request.method} ${request.uri} in ${stopwatch.stop.elapsed(TimeUnit.MILLISECONDS)} ms. [$userAgent]")
    }

  }

  private def processTime(implicit stopwatch: Stopwatch): Int =
    stopwatch.elapsed(TimeUnit.MILLISECONDS).toInt

  protected def lookupToken[A](request: Request[A]): Option[String] = {
    val tokenHeader = request.headers.get("X-Access-Token")
    val tokenCookie = request.cookies.get("X-Access-Token")

    val token = (tokenHeader, tokenCookie) match {
      case (None, None) => None
      case (Some(t1), None) => Some(t1)
      case (None, Some(t2)) => Some(t2.value)
      case (Some(t1), Some(t2)) => Some(t1)
      case _ => None
    }

    token
  }

  private def unauthorized(e: IllegalAccessException): Future[Result] = {
    logger.error(e.getMessage, e)
    Future.successful(Results.Unauthorized)
  }

  private def failed(e: HiddenException): Future[Result] = {
    logger.error(e.getMessage, e)
    Future.successful(Results.Ok)
  }

  private def failed(e: KnownException): Future[Result] = {
    logger.error(e.getMessage, e)
    Future.successful(Results.Ok)
  }

  private def internalservererror(e: Throwable): Future[Result] = {
    logger.error(e.getMessage, e)
    Future.successful(Results.InternalServerError)
  }

}