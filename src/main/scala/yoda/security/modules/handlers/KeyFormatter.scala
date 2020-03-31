/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.handlers

import org.aopalliance.intercept.MethodInvocation

/**
  * @author Peerapat A on Mar 18, 2017
  */
trait KeyFormatter {

  def formatKey(prefix: String, invocation: MethodInvocation): String =
    s"""${prefix}_${invocation.getArguments.map(_.toString).mkString("_")}"""

}
