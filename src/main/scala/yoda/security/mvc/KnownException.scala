/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */
package yoda.security.mvc

/**
  * @author Peerapat A on Jul 13, 2018
  */
class KnownException(val code: String
                     , val message: String
                     , val cause: String
                     , val map: Map[String, String] = Map.empty) extends RuntimeException(cause)

object KnownException {

  private val NA = "NA"

  def apply(code: String): KnownException = new KnownException(code = code, message = NA, cause = NA)

  def apply(code: String, message: String): KnownException = new KnownException(code = code, message = message, cause = message)

  def apply(code: String, message: String, cause: String): KnownException = new KnownException(code = code, message = message, cause = cause)

  def apply(code: String, message: String, cause: String, map: Map[String, String]): KnownException = new KnownException(code = code, message = message, cause = cause, map = map)

}
