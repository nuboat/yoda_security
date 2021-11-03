/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import org.joda.time.DateTime
import yoda.orm.annotations.TableSchema

/**
  * @author Peerapat A on April 14, 2017
  */
@TableSchema(pk = "id", name = "accounts")
case class AccountEntity(id: Long
                         , clientId: Long
                         , isActive: Boolean
                         , isVerify: Boolean
                         , isChangepass: Boolean
                         , accountType: Int
                         , accountRole: Int
                         , username: String
                         , passwordHash: String = null
                         , email: String
                         , firstname: String = null
                         , lastname: String = null
                         , mobileNo: String = null
                         , metaJson: String = "{}"
                         , avatarUrl: String = null
                         , staffCode: String = null
                         , position: String = null
                         , creatorId: Long = 0
                         , created: DateTime = DateTime.now)
