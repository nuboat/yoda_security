/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.cache

import javax.inject.{Inject, Singleton}
import play.api.cache.{AsyncCacheApi, SyncCacheApi}
import yoda.security.mvc.compoments.Cache

import scala.concurrent.duration.Duration

@Singleton
private[modules] class PlayCache @Inject()(sync: SyncCacheApi
                          , async: AsyncCacheApi) extends Cache {

  def get[T: Manifest](key: String): Option[T] = sync.get[T](key)

  def set(key: String, value: Any, expiration: Duration): Unit =
    sync.set(key, value, expiration)

  def asyncset(key: String, value: Any, expiration: Duration): Unit =
    async.set(key, value, expiration)

}
