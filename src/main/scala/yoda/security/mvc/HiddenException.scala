/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

/**
  * @author Peerapat A on Jul 13, 2018
  */
case class HiddenException(code: String
                           , message: String
                           , cause: String) extends RuntimeException(cause){

  def apply(code: String, message: String): HiddenException = HiddenException(code, message, message)

}
