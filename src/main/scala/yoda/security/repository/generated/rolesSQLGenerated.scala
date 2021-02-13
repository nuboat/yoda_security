package yoda.security.repository.generated

import java.sql.{Connection, ResultSet}

import yoda.security.entities.RoleEntity
import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.jtype._
import yoda.orm.PStatement

/**
  * @author Master Norbor
  */
trait rolesSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM roles WHERE id = ?"

  private val INSERT: String = "INSERT INTO roles (id, role_name, creator_id, created) VALUES (?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE roles SET role_name = ?, creator_id = ?, created = ? WHERE id = ?"

  private val DELETE: String = "DELETE FROM roles WHERE id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM roles"

  private val COLUMNS: Set[String] = Set("id", "role_name", "creator_id", "created")

  def insert(e: RoleEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.id)
    .setString(e.roleName)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def get(id: Long)
         (implicit conn: Connection): Option[RoleEntity] = PStatement(QUERY_ID)
    .setLong(id)
    .queryOne(parse)

  def update(e: RoleEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setString(e.roleName)
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

  protected def parse(rs: ResultSet): RoleEntity = RoleEntity(
    id = rs.getLong("id")
    , roleName = rs.getString("role_name")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    