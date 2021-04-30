/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

/**
  * @author Peerapat A on Jul 13, 2018
  */
class HiddenException(val code: String
                      , val message: String
                      , val cause: String
                      , val map: Map[String, String] = Map.empty) extends RuntimeException(cause)

object HiddenException {

  private val NA = "NA"

  def apply(code: String): HiddenException = new HiddenException(code = code, message = NA, cause = NA)

  def apply(code: String, message: String): HiddenException = new HiddenException(code = code, message = message, cause = message)

  def apply(code: String, message: String, cause: String): HiddenException = new HiddenException(code = code, message = message, cause = cause)

  def apply(code: String, message: String, cause: String, map: Map[String, String]): HiddenException = new HiddenException(code = code, message = message, cause = cause, map = map)

}
