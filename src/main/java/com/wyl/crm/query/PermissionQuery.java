package com.wyl.crm.query;

public class PermissionQuery extends BaseQuery {

	private String permissionModelType;// 模块

	private Integer permissionId;
	private String name; // 权限名称
	private String sn; // 权限编号
	private String resource; // 权限编号
	private Long menuId; // 菜单id

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionModelType() {
		return permissionModelType;
	}

	public void setPermissionModelType(String permissionModelType) {
		this.permissionModelType = permissionModelType;
	}

	@Override
	public String toString() {
		return "PermissionQuery [permissionModelType=" + permissionModelType + ", permissionId=" + permissionId
				+ ", name=" + name + ", sn=" + sn + ", resource=" + resource + ", menuId=" + menuId + "]";
	}

}
