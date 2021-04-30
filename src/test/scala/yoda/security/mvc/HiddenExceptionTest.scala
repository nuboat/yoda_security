/*
 * Copyright (c) 2021. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc

import org.scalatest.funsuite.AnyFunSuite

class HiddenExceptionTest extends AnyFunSuite {

  test("1") {
    val t = HiddenException(code = "")
  }


}
