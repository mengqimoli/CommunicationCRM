package com.wyl.crm.domain;

import java.io.Serializable;

/**
 * 权限 创建： 1、手动添加 2、在资源管理模块，点击按钮，自动生成资源对应的权限
 * 
 * @author Administrator
 *
 */
public class Permission implements Serializable {

	private Long id;
	private String name;
	private String sn;
	// private Resource resource;
	private String resource;
	private Integer status = 1;
	private SystemMenu menu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public SystemMenu getMenu() {
		return menu;
	}

	public void setMenu(SystemMenu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", sn=" + sn + ", resource=" + resource + ", status="
				+ status + ", menu=" + menu + "]";
	}

}
