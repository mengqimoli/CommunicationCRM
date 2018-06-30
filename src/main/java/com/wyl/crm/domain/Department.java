package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门模块
 * 
 * 父部门 VS 子部门 1 对 多 1个父部门 拥有 多个子部门 1个子部门 有 1个父部门
 * 
 * @author admin
 *
 */
public class Department implements Serializable {

	private Long id; // 部门id
	private String name; // 部门名称
	private String sn; // 部门编号
	private String dirPath; // 目录路径
	private Integer status = 1; // 部门状态 数字 0 正常 ，-1停用
	private Employee manager;// 部门经理
	private Department parent; // 上级部门
	private List<Department> children = new ArrayList<>();// 子部门

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	// EasyUI兼容
	public String getText() {
		return name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", sn=" + sn + ", name=" + name + ", dirPath=" + dirPath + ", status=" + status
				+ ", manager=" + manager + ", parent=" + parent + ", children=" + children + "]";
	}
}
