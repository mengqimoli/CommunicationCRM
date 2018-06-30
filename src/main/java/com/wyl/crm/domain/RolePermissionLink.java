package com.wyl.crm.domain;

/**
 * 角色权限的中间表对象（用在保存和修改）
 * 
 * @author admin
 *
 */
public class RolePermissionLink {

	private Long role_id;
	private Long permission_id;

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Long getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Long permission_id) {
		this.permission_id = permission_id;
	}

	@Override
	public String toString() {
		return "RolePermissionLink [role_id=" + role_id + ", permission_id=" + permission_id + "]";
	}

	public RolePermissionLink(Long role_id, Long permission_id) {
		super();
		this.role_id = role_id;
		this.permission_id = permission_id;
	}

	public RolePermissionLink() {
		super();
	}

}
