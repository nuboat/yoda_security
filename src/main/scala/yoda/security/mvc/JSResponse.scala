/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */
package yoda.security.mvc

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class JSResponse[T](code: String = "0"
                         , message: String = null
                         , cause: String = null
                         , result: T = null)
