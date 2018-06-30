package com.wyl.crm.query;

/**
 * 部门模块的查询对象
 * 
 * @author admin
 *
 */
public class DepartmentQuery extends BaseQuery {

	private String sn; // 部门编号
	private String name; // 部门名称
	private String managerName; // 经理名称
	private Long parentId; // 上级部门id

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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "DepartmentQuery [sn=" + sn + ", name=" + name + ", managerName=" + managerName + ", parentId="
				+ parentId + ", getCurrentPage()=" + getCurrentPage() + ", getPageSize()=" + getPageSize() + "]";
	}
}
