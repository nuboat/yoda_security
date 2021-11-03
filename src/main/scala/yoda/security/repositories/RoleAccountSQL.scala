/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.repositories

import yoda.orm.PStatement
import yoda.security.entities.RoleAccountEntity
import yoda.security.repositories.generated.roleAccountSQLGenerated

import java.sql.Connection
import javax.inject.Singleton

@Singleton
class RoleAccountSQL extends roleAccountSQLGenerated {

  private val QUERY_BYACCOUNT: String = "SELECT * FROM role_account WHERE account_id = ?"
  def findBy(accountId: Long)
         (implicit conn: Connection): List[RoleAccountEntity] = PStatement(QUERY_BYACCOUNT)
    .setLong(accountId)
    .queryList(parse)

}
