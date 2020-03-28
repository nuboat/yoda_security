/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import in.norbor.yoda.annotations.TableSchema
import org.joda.time.DateTime

/**
  * @author Peerapat A on April 14, 2017
  */
@TableSchema(pk = "id", name = "clients")
case class ClientEntity(id: Long
                        , clientName: String
                        , isActive: Boolean = false
                        , isSingleAccess: Boolean = false
                        , metaJson: String = "{}"
                        , creatorId: Long = 0
                        , created: DateTime = DateTime.now)
