/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.authorize

import play.api.inject.Module
import play.api.{Configuration, Environment}
import yoda.security.mvc.authorize.Authorizer

/**
 * @author Peerapat A on Mar 26, 2019
 */
class BModule extends Module {

  override def bindings(environment: Environment,
                        configuration: Configuration) = Seq(
    bind[Authorizer].to[DatabaseAuthorizer]
  )

}
