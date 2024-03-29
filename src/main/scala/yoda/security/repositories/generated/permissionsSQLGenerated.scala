package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.security.entities.PermissionEntity
import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.jtype._
import yoda.orm.PStatement

/**
  * @author Master Norbor
  */
trait permissionsSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM permissions WHERE id = ?"

  private val INSERT: String = "INSERT INTO permissions (id, method_id, endpoint, creator_id, created) VALUES (?, ?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE permissions SET method_id = ?, endpoint = ?, creator_id = ?, created = ? WHERE id = ?"

  private val DELETE: String = "DELETE FROM permissions WHERE id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM permissions"

  private val COLUMNS: Set[String] = Set("id", "method_id", "endpoint", "creator_id", "created")

  def insert(e: PermissionEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.id)
    .setInt(e.methodId)
    .setString(e.endpoint)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def update(e: PermissionEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setInt(e.methodId)
    .setString(e.endpoint)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .setLong(e.id)
    .update

  def get(id: Long)
         (implicit conn: Connection): Option[PermissionEntity] = PStatement(QUERY_ID)
    .setLong(id)
    .queryOne(parse)

  def delete(id: Long)(implicit conn: Connection): Int = PStatement(DELETE)
    .setLong(id)
    .update

  def count()(implicit conn: Connection): Long = PStatement(COUNT)
    .queryOne(rs => rs.getLong(1))
    .get

  protected def verifyName(p: String): Unit = if (!COLUMNS.contains(p))
    throw new IllegalArgumentException(s"$p has problem.")

  protected def parse(rs: ResultSet): PermissionEntity = PermissionEntity(
    id = rs.getLong("id")
    , methodId = rs.getInt("method_id")
    , endpoint = rs.getString("endpoint")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    