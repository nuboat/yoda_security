/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import org.joda.time.DateTime
import yoda.orm.annotations.TableSchema

/**
  * @author Peerapat A on Dec 7, 2018
  */
@TableSchema(pk = "id", name = "roles")
case class RoleEntity(id: Long
                      , roleName: String
                      , creatorId: Long = 0
                      , created: DateTime = DateTime.now)
