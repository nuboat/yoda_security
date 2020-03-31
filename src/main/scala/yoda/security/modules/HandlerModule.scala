/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import yoda.security.annotations.{Cache, Infolog}
import yoda.security.modules.handlers.{LocalCacheInterceptor, ProfilerInterceptor}

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class HandlerModule() extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bindInterceptor[ProfilerInterceptor](methodMatcher = annotatedWith[Infolog])
    bindInterceptor[LocalCacheInterceptor](methodMatcher = annotatedWith[Cache])
  }

}
