package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerTraceHistory implements Serializable {
	private Long id;
	private Date traceTime; // 跟进时间
	private String traceResult; // 跟进结果
	private String title; // 跟进主题
	private String remark;// 备注
	private Employee seller;// 跟进人员

	private Customer customer;// 客户
	private SystemDictionaryItem traceType;// 跟进方式

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getTraceTime() {
		return traceTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setTraceTime(Date traceTime) {
		this.traceTime = traceTime;
	}

	public String getTraceResult() {
		return traceResult;
	}

	public void setTraceResult(String traceResult) {
		this.traceResult = traceResult;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SystemDictionaryItem getTraceType() {
		return traceType;
	}

	public void setTraceType(SystemDictionaryItem traceType) {
		this.traceType = traceType;
	}

	@Override
	public String toString() {
		return "CustomerTraceHistory [id=" + id + ", traceTime=" + traceTime + ", traceResult=" + traceResult
				+ ", title=" + title + ", remark=" + remark + ", seller=" + seller + ", customer=" + customer
				+ ", traceType=" + traceType + "]";
	}

}
