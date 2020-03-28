/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.json

trait JsonConvert {

  def toJson(obj: AnyRef): String

  def toOption[T: Manifest](body: String): Option[T]

}
