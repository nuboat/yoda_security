/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.compoments

/**
  * @author Peerapat A on Mar 18, 2017
  */
trait KeyFormatter {

  def formatKey(prefix: String, invocation: Array[AnyRef]): String =
    s"""${prefix}_${invocation.map(_.toString).mkString("_")}"""

}
