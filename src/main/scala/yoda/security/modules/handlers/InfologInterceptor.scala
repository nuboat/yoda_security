/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.handlers

import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import org.aopalliance.intercept.{MethodInterceptor, MethodInvocation}
import play.api.cache.{AsyncCacheApi, SyncCacheApi}
import yoda.security.annotations.LocalCache
import yoda.security.mvc.compoments.KeyFormatter

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * @author Peerapat A on Mar 18, 2017
  */
class InfologInterceptor extends MethodInterceptor
  with LazyLogging
  with KeyFormatter {


  override def invoke(invocation: MethodInvocation): AnyRef = {
    val annotated = invocation.getMethod.getAnnotation(classOf[LocalCache])
    val prefix = annotated.prefix()

    val key = try {
      formatKey(prefix, invocation.getArguments)
    } catch {
      case t: Throwable =>
        logger.warn(t.getMessage, t)
    }
    val any =  invocation.proceed()



    any
  }

}
