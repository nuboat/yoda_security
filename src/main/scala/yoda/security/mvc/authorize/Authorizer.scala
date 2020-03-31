/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

/**
  * @author Peerapat A on Mar 26, 2019
  */
trait Authorizer {

  def lookup(token: String): Option[Account]

  def hasAccess(permission: HTTPPermission
                , account: Option[Account]): Boolean

  def publicList: Set[HTTPPermission]

}
