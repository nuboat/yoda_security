package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.security.entities.AccessEntity
import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.jtype._
import yoda.orm.PStatement

/**
  * @author Master Norbor
  */
trait accessesSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM accesses WHERE token = ?"

  private val INSERT: String = "INSERT INTO accesses (token, client_id, account_id, access_role, access_name, meta_json, creator_id, created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE accesses SET client_id = ?, account_id = ?, access_role = ?, access_name = ?, meta_json = ?, creator_id = ?, created = ? WHERE token = ?"

  private val DELETE: String = "DELETE FROM accesses WHERE token = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM accesses"

  private val COLUMNS: Set[String] = Set("token", "client_id", "account_id", "access_role", "access_name", "meta_json", "creator_id", "created")

  def insert(e: AccessEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setString(e.token)
    .setLong(e.clientId)
    .setLong(e.accountId)
    .setInt(e.accessRole)
    .setString(e.accessName)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def update(e: AccessEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setLong(e.clientId)
    .setLong(e.accountId)
    .setInt(e.accessRole)
    .setString(e.accessName)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .setString(e.token)
    .update

  def get(token: String)
         (implicit conn: Connection): Option[AccessEntity] = PStatement(QUERY_ID)
    .setString(token)
    .queryOne(parse)

  def delete(token: String)(implicit conn: Connection): Int = PStatement(DELETE)
    .setString(token)
    .update

  def count()(implicit conn: Connection): Long = PStatement(COUNT)
    .queryOne(rs => rs.getLong(1))
    .get

  protected def verifyName(p: String): Unit = if (!COLUMNS.contains(p))
    throw new IllegalArgumentException(s"$p has problem.")

  protected def parse(rs: ResultSet): AccessEntity = AccessEntity(
    token = rs.getString("token")
    , clientId = rs.getLong("client_id")
    , accountId = rs.getLong("account_id")
    , accessRole = rs.getInt("access_role")
    , accessName = rs.getString("access_name")
    , metaJson = rs.getString("meta_json")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    