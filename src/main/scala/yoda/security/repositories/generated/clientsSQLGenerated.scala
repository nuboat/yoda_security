/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.repositories.generated

import java.sql.{Connection, ResultSet}

import yoda.orm.implicits.JavaSqlImprovement._
import yoda.orm.PStatement
import yoda.security.entities.ClientEntity

/**
  * @author Yoda B
  */
trait clientsSQLGenerated {

  private val QUERY_ID: String = "SELECT * FROM clients WHERE id = ?"

  private val INSERT: String = "INSERT INTO clients (id, client_name, is_active, is_single_access, meta_json, creator_id, created) VALUES (?, ?, ?, ?, ?, ?, ?)"

  private val UPDATE: String = "UPDATE clients SET client_name = ?, is_active = ?, is_single_access = ?, meta_json = ?, creator_id = ?, created = ? WHERE id = ?"

  private val DELETE: String = "DELETE FROM clients WHERE id = ?"

  private val COUNT: String = "SELECT COUNT(1) FROM clients"

  private val COLUMNS: Set[String] = Set("id", "client_name", "is_active", "is_single_access", "meta_json", "creator_id", "created")

  def insert(e: ClientEntity)
            (implicit conn: Connection): Int = PStatement(INSERT)
    .setLong(e.id)
    .setString(e.clientName)
    .setBoolean(e.isActive)
    .setBoolean(e.isSingleAccess)
    .setString(e.metaJson)
    .setLong(e.creatorId)
    .setDateTime(e.created)
    .update

  def get(id: Long)
         (implicit conn: Connection): Option[ClientEntity] = PStatement(QUERY_ID)
    .setLong(id)
    .queryOne(parse)

  def update(e: ClientEntity)
            (implicit conn: Connection): Int = PStatement(UPDATE)
    .setString(e.clientName)
    .setBoolean(e.isActive)
    .setBoolean(e.isSingleAccess)
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

  protected def parse(rs: ResultSet) = ClientEntity(
    id = rs.getLong("id")
    , clientName = rs.getString("client_name")
    , isActive = rs.getBoolean("is_active")
    , isSingleAccess = rs.getBoolean("is_single_access")
    , metaJson = rs.getString("meta_json")
    , creatorId = rs.getLong("creator_id")
    , created = rs.getDateTime("created")
  )

}
    