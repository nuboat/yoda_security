/*
 * Copyright (c) 2021. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import org.scalatest.funsuite.AnyFunSuite

class JacksonImplTest extends AnyFunSuite {

  private val j = new JacksonImpl()

  test("1") {
    val d = j.toJson(Test("Yo"))
    println(d)
  }

  test("2") {
    val d = j.toOption[Test]("""{"name":"Yo"}""")
    println(d)
  }

}

case class Test(name: String)