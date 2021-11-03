package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.security.entities.RolePermissionEntity
import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.jtype._
import yoda.orm.PStatement

/**
  * @author Master Norbor
  */
trait rolePermissionSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM role_permission WHERE role_id = ? AND permission_id = ?"

  private val INSERT: String = "INSERT INTO role_permission (role_id, permission_id, creator_id, created) VALUES (?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE role_permission SET creator_id = ?, created = ? WHERE role_id = ? AND permission_id = ?"

  private val DELETE: String = "DELETE FROM role_permission WHERE role_id = ? AND permission_id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM role_permission"

  private val COLUMNS: Set[String] = Set("role_id", "permission_id", "creator_id", "created")

  def insert(e: RolePermissionEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.roleId)
    .setInt(e.permissionId)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def update(e: RolePermissionEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .setLong(e.roleId)
    .setInt(e.permissionId)
    .update

  def get(roleId: Long, permissionId: Int)
         (implicit conn: Connection): Option[RolePermissionEntity] = PStatement(QUERY_ID)
    .setLong(roleId)
    .setInt(permissionId)
    .queryOne(parse)

  def delete(roleId: Long, permissionId: Int)(implicit conn: Connection): Int = PStatement(DELETE)
    .setLong(roleId)
    .setInt(permissionId)
    .update

  def count()(implicit conn: Connection): Long = PStatement(COUNT)
    .queryOne(rs => rs.getLong(1))
    .get

  protected def verifyName(p: String): Unit = if (!COLUMNS.contains(p))
    throw new IllegalArgumentException(s"$p has problem.")

  protected def parse(rs: ResultSet): RolePermissionEntity = RolePermissionEntity(
    roleId = rs.getLong("role_id")
    , permissionId = rs.getInt("permission_id")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    