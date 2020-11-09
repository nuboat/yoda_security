/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.mvc.authorize

trait PermissionValidation {

  def validate(access: HTTPPermission, permission: HTTPPermission): Boolean = {
    if (access.method != permission.method)
      return false

    if (access.action.endsWith("/**"))
      permission.endpoint.startsWith(access.actionstar)
    else
      access.action == permission.endpoint
  }

}
