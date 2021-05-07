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
                      , val refNo: String) extends RuntimeException(cause)

object HiddenException {

  private val NA = "NA"

  def apply(code: String): HiddenException = new HiddenException(code = code, message = NA, cause = NA, refNo = NA)

  def apply(code: String, message: String): HiddenException = new HiddenException(code = code, message = message, cause = message, refNo = NA)

  def apply(code: String, message: String, cause: String): HiddenException = new HiddenException(code = code, message = message, cause = cause, refNo = NA)

  def apply(code: String, message: String, cause: String, refNo: String): HiddenException = new HiddenException(code = code, message = message, cause = cause, refNo = refNo)

}
