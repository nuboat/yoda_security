/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.compoments

import scala.concurrent.duration.Duration

/**
 * @author Peerapat A on April 13, 2018
 */
trait BCache {

  def get[T: Manifest](key: String): Option[T]

  def set(key: String, value: Any , expiration: Duration): Unit

  def asyncset(key: String, value: Any , expiration: Duration): Unit

}
