/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules

import play.api.inject.Module
import play.api.{Configuration, Environment}
import yoda.security.modules.authorize.DatabaseAuthorizer
import yoda.security.modules.cache.PlayCache
import yoda.security.modules.json.JacksonImpl
import yoda.security.modules.metric.GraphiteMetric
import yoda.security.mvc.authorize.Authorizer
import yoda.security.mvc.compoments.{Cache, Json, Metric}

/**
  * @author Peerapat A on Mar 26, 2019
  */
class OptionModule extends Module {

  override def bindings(environment: Environment,
                        configuration: Configuration) = Seq(
    bind[Cache].to[PlayCache]
    , bind[Json].to[JacksonImpl]
    , bind[Metric].to[GraphiteMetric]
  )

}
