/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.authorize

import javax.inject.{Inject, Singleton}
import play.api.db.Database
import yoda.handlers.LocalCache
import yoda.security.entities.AccessEntity
import yoda.security.mvc.authorize.{Account, Authorizer, HTTPPermission}
import yoda.security.repositories.{AccessSQL, AccountSQL}

/**
 * @author Peerapat A on Mar 26, 2019
 */
@Singleton
private[authorize] class AuthorizerManager @Inject()(db: Database
                                                     , accountSQL: AccountSQL
                                                     , accessSQL: AccessSQL) extends Authorizer {

  override def lookup(token: String): Option[Account] = {
    lookupAccess(token)
      .flatMap(a => lookupAccount(a.accountId).map(_.copy(token = a.token)))
  }

  override def hasAccess(permission: HTTPPermission
                         , account: Option[Account]): Boolean = {
    true
  }

  @LocalCache(prefix = "access_id", timeout = 5)
  private def lookupAccess(token: String): Option[AccessEntity] = db.withConnection { implicit conn =>
    accessSQL.get(token)
  }

  @LocalCache(prefix = "account_id", timeout = 5)
  private def lookupAccount(accountId: Long): Option[Account] = db.withConnection { implicit conn =>
    accountSQL.get(accountId)
      .map(a => Account(
        id = a.id.toString
        , clientId = a.clientId.toString
        , email = a.email
        , username = a.username
        , isActive = a.isActive
        , isVerify = a.isVerify))
  }

}
