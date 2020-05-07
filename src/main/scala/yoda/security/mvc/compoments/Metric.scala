/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.compoments

trait Metric {

  def info(id: String
           , metric: String
           , execution: Int): Unit

}
