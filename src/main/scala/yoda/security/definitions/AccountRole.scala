/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.definitions

/**
  * @author Peerapat A on May 24, 2018
  */
object AccountRole extends Enumeration {

  val Owner: Value = Value(0)

  val Admin: Value = Value(1)

  val Staff: Value = Value(2)

  val Guest: Value = Value(4)

}
