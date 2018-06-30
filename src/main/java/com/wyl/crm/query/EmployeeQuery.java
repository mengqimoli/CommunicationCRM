package com.wyl.crm.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeQuery extends BaseQuery {

	private String username;
	private String realName;
	private String tel;
	private String email;
	private Long deptId;
	private Date inputTimeStart;
	private Date inputTimeEnd;

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getInputTimeStart() {
		return inputTimeStart;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setInputTimeStart(Date inputTimeStart) {
		this.inputTimeStart = inputTimeStart;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getInputTimeEnd() {
		return inputTimeEnd;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setInputTimeEnd(Date inputTimeEnd) {
		this.inputTimeEnd = inputTimeEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeQuery [username=" + username + ", realName=" + realName + ", tel=" + tel + ", email=" + email
				+ ", deptId=" + deptId + ", inputTimeStart=" + inputTimeStart + ", inputTimeEnd=" + inputTimeEnd + "]";
	}

}
