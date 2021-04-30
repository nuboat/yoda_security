/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.internal

import akka.stream.Materializer
import play.api.http.{HttpErrorHandler, ParserConfiguration}
import play.api.libs.Files.TemporaryFileCreator
import play.api.mvc.{BodyParser, PlayBodyParsers}

import javax.inject.Inject

/**
  * @author Peerapat A on Mar 26, 2019
  */
private[mvc] class NonRFPBodyParsers @Inject()(val config: ParserConfiguration,
                                               val errorHandler: HttpErrorHandler,
                                               val materializer: Materializer,
                                               val temporaryFileCreator: TemporaryFileCreator)
  extends PlayBodyParsers {

  private[mvc] def utf8Decoder: BodyParser[String] = {
    val maxLength = DefaultMaxTextLength
    tolerantBodyParser(name = "text"
      , maxLength = maxLength
      , errorMessage = "Error decoding text body") { (request, bytes) =>

      bytes.decodeString(request.charset.getOrElse("UTF-8"))
    }
  }

}
