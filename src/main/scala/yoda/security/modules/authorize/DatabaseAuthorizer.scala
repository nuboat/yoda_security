/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.authorize

import com.typesafe.config.ConfigFactory
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import play.api.http.HttpVerbs
import yoda.security.annotations.LocalCache
import yoda.security.entities.AccessEntity
import yoda.security.mvc.authorize.{Account, Authorizer, HTTPPermission}
import yoda.security.repositories.{AccessSQL, AccountSQL}

import scala.jdk.CollectionConverters._


/**
 * @author Peerapat A on Mar 26, 2019
 */
@Singleton
private[modules] class DatabaseAuthorizer @Inject()(db: Database
                                   , accountSQL: AccountSQL
                                   , accessSQL: AccessSQL) extends Authorizer
  with HttpVerbs {

  private val conf = ConfigFactory.load()
  private val publiclist = conf.getStringList("yoda.security.publiclist").asScala.toList

  override def lookup(token: String): Option[Account] = {
    lookupAccess(token)
      .flatMap(a => lookupAccount(a.accountId).map(_.copy(token = a.token)))
  }

  override def hasAccess(permission: HTTPPermission
                         , account: Option[Account]): Boolean = {

    if (publicList.exists(p => validate(p, permission)))
      return true

    account.isDefined
  }

  override def publicList: Set[HTTPPermission] = publiclist
    .map(a => a.split(" "))
    .map(a => HTTPPermission(a(0), a(1)))
    .toSet

  private def validate(acess: HTTPPermission, permission: HTTPPermission): Boolean = {
    if (acess.method != permission.method)
      return false

    if (acess.action.endsWith("**"))
      acess.action.startsWith(permission.action)
    else
      acess.action == permission.action
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
