/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.compoments

import yoda.security.definitions.Ref

trait Json {

  def toJson(obj: AnyRef): String

  def to[T](body: String, ref: Ref[T]): T

  def toOption[T](body: String, ref: Ref[T]): Option[T]

  def toOption[T: Manifest](body: String): Option[T]

  def prettyStr(o: AnyRef): String

}
