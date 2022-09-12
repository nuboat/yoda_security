/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.authorize

import com.typesafe.config.ConfigFactory
import play.api.db.Database
import play.api.http.HttpVerbs
import yoda.orm.PStatement
import yoda.security.annotations.LocalCache
import yoda.security.definitions.{AccountRole, HTTPMethod}
import yoda.security.entities.AccessEntity
import yoda.security.mvc.authorize.{Account, Authorizer, HTTPPermission, PermissionValidation}
import yoda.security.repositories.{AccessSQL, AccountSQL, RoleAccountSQL}

import javax.inject.{Inject, Singleton}
import scala.jdk.CollectionConverters._


/**
 * @author Peerapat A on Mar 26, 2019
 */
@Singleton
private[modules] class DatabaseAuthorizer @Inject()(private val db: Database
                                                    , private val accountSQL: AccountSQL
                                                    , private val accessSQL: AccessSQL
                                                    , private val raSQL: RoleAccountSQL)
  extends Authorizer
    with HttpVerbs
    with PermissionValidation {

  private val conf = ConfigFactory.load()
  private val publiclist = conf.getStringList("yoda.security.publiclist").asScala.toList

  override def lookup(token: String): Option[Account] = {
    lookupAccess(token)
      .filter(a => a.expire.getMillis > System.currentTimeMillis)
      .flatMap(a => lookupAccount(a.accountId))
  }

  override def hasAccess(permission: HTTPPermission
                         , optAccount: Option[Account]): Boolean = {

    if (publicList.exists(p => validate(p, permission)))
      return true

    if (optAccount.isEmpty || optAccount.get.notAllow)
      return false

    val account = optAccount.get
    val roles = hasRoles(account.id.toLong) + 0L
    if (Set(AccountRole.Owner, AccountRole.Admin).contains(account.accountRole)
      || allowList(roles).exists(p => validate(p, permission)))
      return true

    account.permissions.exists(p => validate(p, permission))
  }

  override def publicList: Set[HTTPPermission] = publiclist
    .map(a => a.split(" "))
    .map(a => HTTPPermission(a(0), a(1)))
    .toSet

  @LocalCache(prefix = "role_account")
  private def hasRoles(accountId: Long): Set[Long] = db
    .withConnection { implicit conn =>
      raSQL.findBy(accountId)
        .map(_.roleId)
        .toSet
    }

  @LocalCache(prefix = "role_permission")
  private def allowList(roleIds: Set[Long]): Set[HTTPPermission] = db
    .withConnection { implicit conn =>
      val p = PStatement(
        """
          |SELECT  p.method_id, p.endpoint
          |FROM    role_permission rp
          |    INNER JOIN permissions p on rp.permission_id = p.id
          |WHERE   rp.role_id = ?;
          |""".stripMargin)

      roleIds.flatMap(roleId =>
        p.setLong(roleId).queryList(rs => HTTPPermission(
          method = HTTPMethod(rs.getInt("method_id")).toString
          , action = rs.getString("endpoint")
        )))
    }

  @LocalCache(prefix = "access_id")
  private def lookupAccess(token: String): Option[AccessEntity] = db.withConnection { implicit conn =>
    accessSQL.get(token)
  }

  @LocalCache(prefix = "account_id")
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
