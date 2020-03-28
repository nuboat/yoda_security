/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class Account(id: String
              , clientId: String = null
              , email: String = null
              , token: String = null
              , username: String = null
              , params: Map[String, String] = Map.empty
              , isActive: Boolean = true
              , isVerify: Boolean = true
              , isChangePass: Boolean = false
              , roles: Set[String] = Set.empty
              , permissions: Set[String] = Set.empty) {

  def apply(key: String): Option[String] = params.get(key)

}
