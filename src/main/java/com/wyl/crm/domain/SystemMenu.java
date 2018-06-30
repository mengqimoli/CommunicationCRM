package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SystemMenu implements Serializable{

	private Long id;
	private String name;
	private String sn;
	private String url;
	private String icon;
	private String intro;
	private SystemMenu parent;
	// 定义集合来装字菜单;
	private List<SystemMenu> children = new ArrayList<>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public SystemMenu getParent() {
		return parent;
	}

	public void setParent(SystemMenu parent) {
		this.parent = parent;
	}

	public List<SystemMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SystemMenu> children) {
		this.children = children;
	}

	// EasyUI兼容
	public String getText() {
		return name;
	}

	@Override
	public String toString() {
		return "SystemMenu [id=" + id + ", name=" + name + ", sn=" + sn + ", url=" + url + ", icon=" + icon + ", intro="
				+ intro + ", parent=" + parent + ", children=" + children + "]";
	}

}
