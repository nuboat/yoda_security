/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */
import in.norbor.yoda.definitions.NamingConvention
import in.norbor.yoda.generator.{Generator, Target}
import yoda.security.entities._

/**
  * @author Peerapat A Nov 1, 2018
  */
private object DbGenerator extends App {

  val g = Generator(NamingConvention.CamelToSnakecase)
  implicit val target: Target = Target(target = "."
    , packages = Array("yoda", "security", "repository"))

  g.gen[AccessEntity](table = "accesses", idName = "token", idType = "String")
//  g.gen[AccountEntity](table = "accounts", idName = "id", idType = "Long")
//  g.gen[ClientEntity](table = "clients", idName = "id", idType = "Long")
//  g.gen[PermissionEntity](table = "permissions", idName = "id", idType = "Long")
//  g.gen[RoleEntity](table = "roles", idName = "id", idType = "Long")
//  g.gen[RolePermissionEntity](table = "role_permission", idName = "id", idType = "Long")

}
