/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.authorize

import com.typesafe.config.ConfigFactory
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import play.api.http.HttpVerbs
import yoda.security.annotations.LocalCache
import yoda.security.definitions.AccountRole
import yoda.security.entities.AccessEntity
import yoda.security.mvc.authorize.{Account, Authorizer, HTTPPermission, PermissionValidation}
import yoda.security.repositories.{AccessSQL, AccountSQL}

import scala.jdk.CollectionConverters._


/**
  * @author Peerapat A on Mar 26, 2019
  */
@Singleton
private[modules] class DatabaseAuthorizer @Inject()(db: Database
                                                    , accountSQL: AccountSQL
                                                    , accessSQL: AccessSQL) extends Authorizer
  with HttpVerbs
  with PermissionValidation {

  private val conf = ConfigFactory.load()
  private val publiclist = conf.getStringList("yoda.security.publiclist").asScala.toList

  override def lookup(token: String): Option[Account] = {
    lookupAccess(token)
      .flatMap(a => lookupAccount(a.accountId))
  }

  override def hasAccess(permission: HTTPPermission
                         , optAccount: Option[Account]): Boolean = {

    if (publicList.exists(p => validate(p, permission)))
      return true

    if (optAccount.isEmpty)
      return false

    val account = optAccount.get
    if (account.notAllow)
      return false

    if (Set(AccountRole.Owner, AccountRole.Admin).contains(account.accountRole))
      return true

    account.permissions.exists(p => validate(p, permission))
  }

  override def publicList: Set[HTTPPermission] = publiclist
    .map(a => a.split(" "))
    .map(a => HTTPPermission(a(0), a(1)))
    .toSet

  @LocalCache(prefix = "access_id", timeout = 5)
  private def lookupAccess(token: String): Option[AccessEntity] = db.withConnection { implicit conn =>
    accessSQL.get(token)
  }

  @LocalCache(prefix = "account_id", timeout = 5)
  private def lookupAccount(accountId: Long): Option[Account] = db.withConnection { implicit conn =>
    accountSQL.get(accountId)
      .map(a => Account(
        id = a.id.toString
        , clientId = a.clientId
        , username = a.username
        , isVerify = a.isVerify
        , isActive = a.isActive
        , isChangePass = a.isChangepass
        , accountRole = AccountRole.apply(a.accountRole)))
  }

}
