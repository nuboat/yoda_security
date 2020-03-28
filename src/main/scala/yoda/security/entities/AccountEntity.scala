/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.entities

import in.norbor.yoda.annotations.TableSchema
import org.joda.time.DateTime
import yoda.security.definitions.{AccountRole, AccountType}

/**
  * @author Peerapat A on April 14, 2017
  */
@TableSchema(pk = "id", name = "accounts")
case class AccountEntity(id: Long
                         , clientId: Long
                         , isActive: Boolean
                         , isVerify: Boolean
                         , isChangepass: Boolean
                         , accountType: Int = AccountType.Individual.id
                         , accountRole: Int = AccountRole.Owner.id
                         , username: String
                         , passwordHash: String = null
                         , email: String
                         , firstname: String = null
                         , lastname: String = null
                         , mobileNo: String = null
                         , metaJson: String = "{}"
                         , creatorId: Long = 0
                         , created: DateTime = DateTime.now)
