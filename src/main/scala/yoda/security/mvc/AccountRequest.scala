/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import play.api.mvc.{Request, WrappedRequest}
import yoda.security.mvc.authorize.Account

/**
 * @author Peerapat A on Mar 26, 2019
 */
case class AccountRequest[A](request: Request[A]
                             , reqUUID: String = "NA"
                             , optAccount: Option[Account])
                            (implicit stopwatch: Stopwatch)
  extends WrappedRequest(request) {

  val account: Account = optAccount.orNull

  def processTime: Int = stopwatch.elapsed(TimeUnit.MILLISECONDS).toInt

}
