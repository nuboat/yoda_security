/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.internal

import javax.inject.Inject
import play.api.http.FileMimeTypes
import play.api.i18n.{Langs, MessagesApi}
import play.api.mvc.{ControllerComponents, DefaultActionBuilder}

import scala.concurrent.ExecutionContext

/**
 * @author Peerapat A on Mar 26, 2019
 */
private[mvc] case class StandardComponents @Inject()(actionBuilder: DefaultActionBuilder
                                                     , executionContext: ExecutionContext
                                                     , fileMimeTypes: FileMimeTypes
                                                     , langs: Langs
                                                     , messagesApi: MessagesApi
                                                     , manageAction: ManageAction
                                                     , parsers: NonRFPBodyParsers)
  extends ControllerComponents
