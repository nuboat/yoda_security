/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers._

class PermissionValidationTest extends AnyFunSuite
  with should.Matchers
  with PermissionValidation {

  private val allow = HTTPPermission("POST", "/epassportkyc/**")
  private val method = "POST"

  private val begin1 = "/epassportkyc/begin"
  test(s"$method $begin1") {
    validate(allow, HTTPPermission(method, begin1)) should be (true)
  }

  private val begin2 = "/epassportkyc/begin?refid=12"
  test(s"$method $begin2") {
    validate(allow, HTTPPermission(method, begin2)) should be (true)
  }

  private val commit1 = "/dipchipkyc/commit"
  test(s"$method $commit1") {
    validate(allow, HTTPPermission(method, commit1)) should be (false)
  }

}
