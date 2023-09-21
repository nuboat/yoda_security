/*
 * Copyright (c) 2021. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import org.joda.time.DateTime
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

  test("3") {
    val d = j.toOption[Test]("""{"name":"Yo"}""")
    assert(d.get.name === "Yo")
  }

  test("4") {
    val now = TestDate(DateTime.now())
    println(j.toJson(now))
    println(j.prettyStr(now))
  }

  test("5") {
    val now = TestDate(DateTime.now())
    val s = j.toJson(now)
    val d = j.toOption[TestDate](s)

    assert(now === d.get)
  }

}

case class Test(name: String)
case class TestDate(created: DateTime)