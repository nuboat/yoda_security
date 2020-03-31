/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.metric

import play.api.inject.Module
import play.api.{Configuration, Environment}
import yoda.security.mvc.compoments.BMetric

/**
 * @author Peerapat A on Mar 26, 2019
 */
class BModule extends Module {

  override def bindings(environment: Environment,
                        configuration: Configuration) = Seq(
    bind[BMetric].to[GraphiteMetric]
  )

}
