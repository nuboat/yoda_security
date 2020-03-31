/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.handlers

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.LazyLogging
import org.aopalliance.intercept.{MethodInterceptor, MethodInvocation}

/**
  * @author Peerapat A on Mar 18, 2017
  */
class ProfilerInterceptor extends MethodInterceptor
  with LazyLogging {

  override def invoke(invocation: MethodInvocation): AnyRef = {
    val watch = Stopwatch.createStarted()
    val className = invocation.getMethod.getDeclaringClass.getSimpleName
    val methodName = invocation.getMethod.getName

    try {
      invocation.proceed()
    } finally {
      val elapsed = watch.stop.elapsed(TimeUnit.MILLISECONDS)
      logger.info(s"$className.$methodName, execute in $elapsed ms.")
    }
  }

}
