package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

	private Long id;

	private String sn;

	private String name;

	private String intro;

	/**
	 * 角色 和 权限 多 多
	 */
	private List<Permission> permissions = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Role(Long id) {
		super();
		this.id = id;
	}

	public Role() {
		super();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", sn=" + sn + ", name=" + name + ", permissions=" + permissions + "]";
	}
}
