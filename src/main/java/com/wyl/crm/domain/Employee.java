package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee implements Serializable {

	private Long id; // ID 数据库自动生成
	private String username;// 员工账号 文本
	private String password = "000000";// 密码 文本
	private String realName;// 真实姓名 文本
	private String tel;// 电话 文本
	private String email;// 邮箱 文本
	private Date inputTime;// 录入时间 日期
	// 0 正常 ，-1离职
	private Integer status = 1;// 状态 数字 1 正常 ，-1离职
	private List<Role> roles = new ArrayList<>();
	private Department dept; // 部门 文本

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getId() {
		return id;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getInputTime() {
		return inputTime;
	}

	// 前台向后台
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", realName=" + realName
				+ ", tel=" + tel + ", email=" + email + ", inputTime=" + inputTime + ", status=" + status + ", dept="
				+ dept + "]";
	}

}
