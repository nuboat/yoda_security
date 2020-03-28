/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import in.norbor.yoda.implicits.JavaSqlImprovement._
import in.norbor.yoda.orm.PStatement
import yoda.security.entities.RolePermissionEntity

/**
  * @author Yoda B
  */
trait rolePermissionSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM role_permission WHERE id = ?"

  private val INSERT: String = "INSERT INTO role_permission (id, rold_id, permission_id, meta_json, creator_id, created) VALUES (?, ?, ?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE role_permission SET rold_id = ?, permission_id = ?, meta_json = ?, creator_id = ?, created = ? WHERE id = ?"

  private val DELETE: String = "DELETE FROM role_permission WHERE id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM role_permission"

  private val COLUMNS: Set[String] = Set("id", "rold_id", "permission_id", "meta_json", "creator_id", "created")

  def insert(e: RolePermissionEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.id)
    .setLong(e.roldId)
    .setInt(e.permissionId)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def get(id: Long)
         (implicit conn: Connection): Option[RolePermissionEntity] = PStatement(QUERY_ID)
    .setLong(id)
    .queryOne(parse)

  def update(e: RolePermissionEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setLong(e.roldId)
    .setInt(e.permissionId)
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

  protected def parse(rs: ResultSet) = RolePermissionEntity(
    id = rs.getLong("id")
    , roldId = rs.getLong("rold_id")
    , permissionId = rs.getInt("permission_id")
    , metaJson = rs.getString("meta_json")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    