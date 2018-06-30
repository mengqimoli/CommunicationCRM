package com.wyl.crm.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDevPlanQuery extends BaseQuery {

	private String planSubject;
	private String planDetails;
	private Long planTypeId;
	private String poteId;
	private String inputUserId;
	private Date planTimeStart;
	private Date planTimeEnd;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getPlanTimeStart() {
		return planTimeStart;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPlanTimeStart(Date planTimeStart) {
		this.planTimeStart = planTimeStart;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getPlanTimeEnd() {
		return planTimeEnd;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPlanTimeEnd(Date planTimeEnd) {
		this.planTimeEnd = planTimeEnd;
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

	public Long getPlanTypeId() {
		return planTypeId;
	}

	public void setPlanTypeId(Long planTypeId) {
		this.planTypeId = planTypeId;
	}

	public String getPoteId() {
		return poteId;
	}

	public void setPoteId(String poteId) {
		this.poteId = poteId;
	}

	public String getInputUserId() {
		return inputUserId;
	}

	public void setInputUserId(String inputUserId) {
		this.inputUserId = inputUserId;
	}

}
