/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security

import com.fasterxml.jackson.core.`type`.TypeReference

/**
 * @author Peerapat A on Mar 26, 2019
 */
package object definitions {

  type Ref[T] = TypeReference[T]

  type AccessRole = AccessRole.Value

  type AccountRole = AccountRole.Value

  type AccountType = AccountType.Value

}
