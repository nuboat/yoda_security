/*
 * Copyright (c) 2021. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import org.scalatest.funsuite.AnyFunSuite
import yoda.security.definitions.Ref

class JacksonImplTest extends AnyFunSuite {

  private val j = new JacksonImpl()

  test("1") {
    val d = j.toJson(Test("Yo"))
    println(d)
  }

  test("2") {
    val d = j.toOption("""{"name":"Yo"}""", new Ref[Test]{})
    assert(d.get.name === "Yo")
  }

}

case class Test(name: String)