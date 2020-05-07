/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

/**
 * @author Peerapat A on Mar 26, 2020
 */
case class KnownException(code: String
                          , message: String
                          , cause: String) extends RuntimeException(s"$message : $cause") {

  def apply(code: String, message: String): KnownException = KnownException(code, message, message)

}