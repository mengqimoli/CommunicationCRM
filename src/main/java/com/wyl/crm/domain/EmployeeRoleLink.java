package com.wyl.crm.domain;

public class EmployeeRoleLink {

	private Long employee_id;
	private Long role_id;

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "EmployeeRoleLink [employee_id=" + employee_id + ", role_id=" + role_id + "]";
	}

	public EmployeeRoleLink(Long employee_id, Long role_id) {
		super();
		this.employee_id = employee_id;
		this.role_id = role_id;
	}

	public EmployeeRoleLink() {
		super();
	}

}
