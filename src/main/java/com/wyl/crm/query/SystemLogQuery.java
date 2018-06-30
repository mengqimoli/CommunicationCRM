package com.wyl.crm.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SystemLogQuery extends BaseQuery {

	private String username; // 操作用户
	private String opIp; // 登录ip
	private String function; // 操作内容
	private Date inputTimeStart;
	private Date inputTimeEnd;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInputTimeStart() {
		return inputTimeStart;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInputTimeStart(Date inputTimeStart) {
		this.inputTimeStart = inputTimeStart;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInputTimeEnd() {
		return inputTimeEnd;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInputTimeEnd(Date inputTimeEnd) {
		this.inputTimeEnd = inputTimeEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "SystemLogQuery [username=" + username + ", opIp=" + opIp + ", function=" + function + "]";
	}

}
