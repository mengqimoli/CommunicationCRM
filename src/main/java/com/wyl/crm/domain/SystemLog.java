package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SystemLog implements Serializable {

	private Long id;

	private Employee opUser; // 操作人

	private String opIp;// 操作ip

	private Date opTime;// 操作时间

	// 员工管理:保存
	// cn.itsource.crm.service.impl.DepartmentServiceImpl:save
	private String function;// 操作信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getOpUser() {
		return opUser;
	}

	public void setOpUser(Employee opUser) {
		this.opUser = opUser;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getOpTime() {
		return opTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpIp() {
		return opIp;
	}

	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}
