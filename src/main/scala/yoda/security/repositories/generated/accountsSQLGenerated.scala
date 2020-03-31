/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.PStatement
import yoda.security.entities.AccountEntity

/**
  * @author Yoda B
  */
trait accountsSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM accounts WHERE id = ?"

  private val INSERT: String = "INSERT INTO accounts (id, client_id, is_active, is_verify, is_changepass, account_type, account_role, username, password_hash, email, firstname, lastname, mobile_no, meta_json, creator_id, created) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE accounts SET client_id = ?, is_active = ?, is_verify = ?, is_changepass = ?, account_type = ?, account_role = ?, username = ?, password_hash = ?, email = ?, firstname = ?, lastname = ?, mobile_no = ?, meta_json = ?, creator_id = ?, created = ? WHERE id = ?"

  private val DELETE: String = "DELETE FROM accounts WHERE id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM accounts"

  private val COLUMNS: Set[String] = Set("id", "client_id", "is_active", "is_verify", "is_changepass", "account_type", "account_role", "username", "password_hash", "email", "firstname", "lastname", "mobile_no", "meta_json", "creator_id", "created")

  def insert(e: AccountEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.id)
    .setLong(e.clientId)
    .setBoolean(e.isActive)
    .setBoolean(e.isVerify)
    .setBoolean(e.isChangepass)
    .setInt(e.accountType)
    .setInt(e.accountRole)
    .setString(e.username)
    .setString(e.passwordHash)
    .setString(e.email)
    .setString(e.firstname)
    .setString(e.lastname)
    .setString(e.mobileNo)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def get(id: Long)
         (implicit conn: Connection): Option[AccountEntity] = PStatement(QUERY_ID)
    .setLong(id)
    .queryOne(parse)

  def update(e: AccountEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setLong(e.clientId)
    .setBoolean(e.isActive)
    .setBoolean(e.isVerify)
    .setBoolean(e.isChangepass)
    .setInt(e.accountType)
    .setInt(e.accountRole)
    .setString(e.username)
    .setString(e.passwordHash)
    .setString(e.email)
    .setString(e.firstname)
    .setString(e.lastname)
    .setString(e.mobileNo)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .setLong(e.id)
    .update

  def delete(id: Long)(implicit conn: Connection): Int = PStatement(DELETE)
    .setLong(id)
    .update

  def count()(implicit conn: Connection): Long = PStatement(COUNT)
    .queryOne(rs => rs.getLong(1))
    .get

  protected def verifyName(p: String): Unit = if (!COLUMNS.contains(p)) throw new IllegalArgumentException(s"$p has problem.")

  protected def parse(rs: ResultSet) = AccountEntity(
    id = rs.getLong("id")
    , clientId = rs.getLong("client_id")
    , isActive = rs.getBoolean("is_active")
    , isVerify = rs.getBoolean("is_verify")
    , isChangepass = rs.getBoolean("is_changepass")
    , accountType = rs.getInt("account_type")
    , accountRole = rs.getInt("account_role")
    , username = rs.getString("username")
    , passwordHash = rs.getString("password_hash")
    , email = rs.getString("email")
    , firstname = rs.getString("firstname")
    , lastname = rs.getString("lastname")
    , mobileNo = rs.getString("mobile_no")
    , metaJson = rs.getString("meta_json")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    