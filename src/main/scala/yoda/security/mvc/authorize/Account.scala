/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

import yoda.security.definitions.AccountRole

/**
  * permissions = POST /action
  *
  * @author Peerapat A on Mar 26, 2019
  */
case class Account(id: String
                   , clientId: Long
                   , username: String
                   , isVerify: Boolean
                   , isActive: Boolean
                   , isChangePass: Boolean
                   , accountRole: AccountRole
                   , extra: Option[String] = None
                   , roles: Set[String] = Set.empty
                   , permissions: Set[HTTPPermission] = Set.empty) {

  val isAllow: Boolean = isActive & !isChangePass

  val notAllow: Boolean = !isAllow

}
