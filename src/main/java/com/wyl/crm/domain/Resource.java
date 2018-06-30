package com.wyl.crm.domain;

import java.io.Serializable;

/**
 * 资源管理
 * 
 * @author Administrator
 *
 */
public class Resource implements Serializable{

	private Long id;

	// xxx.xxx.xx:save()
	private String name;

	private String url;

	private String controller;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	// EasyUI兼容
	public String getText() {
		return name;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + "]";
	}
}
