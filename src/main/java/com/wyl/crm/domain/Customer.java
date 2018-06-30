package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer implements Serializable {
	private Long id;
	private String name; // 客户
	private String contacts; // 联系人
	private Integer age; // 客户年龄
	private boolean sex; // 客户性别
	private String tel; // 客户电话
	private String email; // 客户邮箱
	private String qq; // 客户qq
	private String wechat; // 客户微信
	private Date inputTime; // 录入时间
	private Employee inputUser;// 录入人员
	private Employee seller;// 营销人员
	private SystemDictionaryItem job; // 客户职业(9-11)
	private SystemDictionaryItem salaryLevel; // 客户收入阶梯(12-17)
	private SystemDictionaryItem customerSource; // 客户来源(1网上查找2熟人介绍3客户转交)
	private SystemDictionaryItem customerStatus;// 客户状态(22意向客户23定金客户24合同客户25保修客户)
	private Integer status = 1; // 服务等级(1普通用户2中级客户3高级客户4VIP客户)
	private Integer pool;// 是否放入资源池(1是-1否)

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getPool() {
		return pool;
	}

	public void setPool(Integer pool) {
		this.pool = pool;
	}

	public SystemDictionaryItem getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(SystemDictionaryItem customerStatus) {
		this.customerStatus = customerStatus;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInputTime() {
		return inputTime;
	}

	// 前台向后台
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public SystemDictionaryItem getJob() {
		return job;
	}

	public void setJob(SystemDictionaryItem job) {
		this.job = job;
	}

	public SystemDictionaryItem getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(SystemDictionaryItem salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public SystemDictionaryItem getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(SystemDictionaryItem customerSource) {
		this.customerSource = customerSource;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", tel=" + tel + ", email="
				+ email + ", qq=" + qq + ", wechat=" + wechat + ", inputTime=" + inputTime + ", inputUser=" + inputUser
				+ ", seller=" + seller + ", job=" + job + ", salaryLevel=" + salaryLevel + ", customerSource="
				+ customerSource + ", customerStatus=" + customerStatus + ", status=" + status + "]";
	}

}
