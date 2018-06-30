package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PotentialCustomer implements Serializable {

	private Long id; // 编号
	private String name; // 客户名称
	private Integer age; // 年龄
	private boolean sex; // 性别
	private String intro; // 客户描述
	private String linkMan; // 联系人
	private String linkManTel; // 联系人电话
	private Date inputTime; // 录入时间
	private String successRate; // 成功几率
	private Integer status = 1; // 状态

	private Employee inputUser; // 录入人
	private Employee seller; // 营销人员
	private SystemDictionaryItem customerSource; // 客户来源，来自数据字典

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLinkManTel() {
		return linkManTel;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

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

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	// 从后台向前台
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInputTime() {
		return inputTime;
	}

	// 前台向后台
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public SystemDictionaryItem getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(SystemDictionaryItem customerSource) {
		this.customerSource = customerSource;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PotentialCustomer [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", intro=" + intro
				+ ", linkMan=" + linkMan + ", linkManTel=" + linkManTel + ", inputTime=" + inputTime + ", successRate="
				+ successRate + ", status=" + status + ", inputUser=" + inputUser + ", customerSource=" + customerSource
				+ "]";
	}

}
