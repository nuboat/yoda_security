package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.security.entities.RoleAccountEntity
import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.jtype._
import yoda.orm.PStatement

/**
  * @author Master Norbor
  */
trait roleAccountSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM role_account WHERE role_id = ? AND account_id = ?"

  private val INSERT: String = "INSERT INTO role_account (role_id, account_id, creator_id, created) VALUES (?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE role_account SET creator_id = ?, created = ? WHERE role_id = ? AND account_id = ?"

  private val DELETE: String = "DELETE FROM role_account WHERE role_id = ? AND account_id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM role_account"

  private val COLUMNS: Set[String] = Set("role_id", "account_id", "creator_id", "created")

  def insert(e: RoleAccountEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.roleId)
    .setInt(e.accountId)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def update(e: RoleAccountEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .setLong(e.roleId)
    .setInt(e.accountId)
    .update

  def get(roleId: Long, accountId: Int)
         (implicit conn: Connection): Option[RoleAccountEntity] = PStatement(QUERY_ID)
    .setLong(roleId)
    .setInt(accountId)
    .queryOne(parse)

  def delete(roleId: Long, accountId: Int)(implicit conn: Connection): Int = PStatement(DELETE)
    .setLong(roleId)
    .setInt(accountId)
    .update

  def count()(implicit conn: Connection): Long = PStatement(COUNT)
    .queryOne(rs => rs.getLong(1))
    .get

  protected def verifyName(p: String): Unit = if (!COLUMNS.contains(p))
    throw new IllegalArgumentException(s"$p has problem.")

  protected def parse(rs: ResultSet): RoleAccountEntity = RoleAccountEntity(
    roleId = rs.getLong("role_id")
    , accountId = rs.getInt("account_id")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    