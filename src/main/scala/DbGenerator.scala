/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */


import yoda.orm.definitions.NamingConvention
import yoda.orm.generator.{Generator, Target}
import yoda.security.entities._

/**
  * @author Peerapat A Nov 1, 2018
  */
object DbGenerator extends App {

  val g = Generator(NamingConvention.CamelToSnakecase)
  implicit val target: Target = Target(target = "./src/main/scala"
    , packages = Array("yoda", "security", "repository", "generated"))

  g.gen[AccessEntity](table = "accesses", Seq("token"))
  g.gen[AccountEntity](table = "accounts", Seq("id"))
  g.gen[ClientEntity](table = "clients", Seq("id"))
  g.gen[PermissionEntity](table = "permissions", Seq("id"))
  g.gen[RoleEntity](table = "roles", Seq("id"))
  g.gen[RolePermissionEntity](table = "role_permission", Seq("id"))

}
