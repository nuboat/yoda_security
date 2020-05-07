/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.handlers

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import yoda.security.annotations.{Infolog, LocalCache, Profiler}

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class HandlerModule() extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bindInterceptor[InfologInterceptor](methodMatcher = annotatedWith[Infolog])
    bindInterceptor[LocalCacheInterceptor](methodMatcher = annotatedWith[LocalCache])
    bindInterceptor[ProfilerInterceptor](methodMatcher = annotatedWith[Profiler])
  }

}
