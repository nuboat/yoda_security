/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import org.joda.time.DateTime
import yoda.orm.annotations.TableSchema

/**
  * @author Peerapat A on April 14, 2017
  */
@TableSchema(pk = "id", name = "permissions")
case class PermissionEntity(id: Long
                            , methodId: Int
                            , endpoint: String
                            , accessId: Int
                            , metaJson: String = "{}"
                            , creatorId: Long = 0
                            , created: DateTime = DateTime.now)
