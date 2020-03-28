/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import in.norbor.yoda.annotations.TableSchema
import org.joda.time.DateTime
import yoda.security.definitions.AccessRole

/**
  * @author Peerapat A on April 14, 2017
  */
@TableSchema(pk = "token", name = "accesses")
case class AccessEntity(token: String
                        , clientId: Long
                        , accountId: Long
                        , accessRole: Int = AccessRole.Signin.id
                        , accessName: String = "NA"
                        , metaJson: String = "{}"
                        , creatorId: Long = 0
                        , created: DateTime = DateTime.now)
