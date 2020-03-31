/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.handlers

import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import org.aopalliance.intercept.{MethodInterceptor, MethodInvocation}
import play.api.cache.{AsyncCacheApi, SyncCacheApi}
import yoda.security.annotations.Cache

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * @author Peerapat A on Mar 18, 2017
  */
class LocalCacheInterceptor extends MethodInterceptor
  with LazyLogging
  with KeyFormatter {

  @Inject
  var sync: SyncCacheApi = _

  @Inject
  var async: AsyncCacheApi = _

  override def invoke(invocation: MethodInvocation): AnyRef = {
    val annotated = invocation.getMethod.getAnnotation(classOf[Cache])
    val prefix = annotated.prefix()
    val key = try {
      formatKey(prefix, invocation)
    } catch {
      case t: Throwable =>
        logger.warn(t.getMessage, t)
        return invocation.proceed()
    }

    val cache = sync.get[AnyRef](key)
    if (cache.isDefined) {
      logger.debug(s"Cache: $key -> ${cache.get.toString}")
      return cache.get
    }

    val any = invocation.proceed()
    val timeout = annotated.timeout()
    async.set(key, any, timeout minutes)
    logger.debug(s"Put: $key -> ${any.toString} to Cache: ")

    any
  }

}
