package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDevPlan implements Serializable {
	private Long id;
	private Date planTime; // 开发计划时间
	private String planSubject; // 开发计划主题
	private String planDetails; // 开发计划详情
	private Date inputTime; // 录入时间
	private SystemDictionaryItem planType; // 计划实施方式
	private Employee inputUser; // 创建人
	private Employee seller; // 营销人员
	private PotentialCustomer pote;// 潜在客户
	private Integer result;// 开发结果

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getPlanTime() {
		return planTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getPlanSubject() {
		return planSubject;
	}

	public void setPlanSubject(String planSubject) {
		this.planSubject = planSubject;
	}

	public String getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(String planDetails) {
		this.planDetails = planDetails;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInputTime() {
		return inputTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public SystemDictionaryItem getPlanType() {
		return planType;
	}

	public void setPlanType(SystemDictionaryItem planType) {
		this.planType = planType;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	public PotentialCustomer getPote() {
		return pote;
	}

	public void setPote(PotentialCustomer pote) {
		this.pote = pote;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "CustomerDevPlan [id=" + id + ", planTime=" + planTime + ", planSubject=" + planSubject
				+ ", planDetails=" + planDetails + ", inputTime=" + inputTime + ", planType=" + planType
				+ ", inputUser=" + inputUser + ", pote=" + pote + "]";
	}

}
