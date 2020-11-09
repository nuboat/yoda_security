/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class HTTPPermission(method: String, action: String) {

    val actionstar: String = action.replace("/**", "")

    val endpoint: String = action.split("\\?").head

}
