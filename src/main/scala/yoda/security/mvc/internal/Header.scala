/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.internal

/**
 * @author Peerapat A on April 13, 2018
 */
private[mvc] trait Header {

  private val HEADERS: List[(String, String)] = List("Cache-Control" -> "no-cache")

  def headers(processInMs: Int): Seq[(String, String)]  = {
    ("X-Execution-ms", s"$processInMs") :: HEADERS
  }

}
