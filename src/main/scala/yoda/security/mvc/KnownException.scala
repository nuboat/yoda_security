/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */
package yoda.security.mvc

import yoda.security.mvc.HiddenException.NA

/**
  * @author Peerapat A on Jul 13, 2018
  */
class KnownException(val code: String
                     , val message: String
                     , val cause: String
                     , val refNo: String) extends RuntimeException(cause)

object KnownException {

  private val NA = "NA"

  def apply(code: String): KnownException = new KnownException(code = code, message = NA, cause = NA, refNo = NA)

  def apply(code: String, message: String): KnownException = new KnownException(code = code, message = message, cause = message, refNo = NA)

  def apply(code: String, message: String, cause: String): KnownException = new KnownException(code = code, message = message, cause = cause, refNo = NA)

  def apply(code: String, message: String, cause: String, refNo: String): KnownException = new KnownException(code = code, message = message, cause = cause, refNo = refNo)

}
