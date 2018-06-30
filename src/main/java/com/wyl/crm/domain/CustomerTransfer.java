package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerTransfer implements Serializable {
	private Long id;
	private Date transTime;// 移交时间
	private String transReason;// 移交原因
	private Employee oldSeller;// 老市场专员
	private Employee newSeller;// 新市场专员
	private Customer customer;// 客户
	private Employee transUser;// 移交人员

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getTransTime() {
		return transTime;
	}

	// 前台向后台
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public String getTransReason() {
		return transReason;
	}

	public void setTransReason(String transReason) {
		this.transReason = transReason;
	}

	public Employee getOldSeller() {
		return oldSeller;
	}

	public void setOldSeller(Employee oldSeller) {
		this.oldSeller = oldSeller;
	}

	public Employee getNewSeller() {
		return newSeller;
	}

	public void setNewSeller(Employee newSeller) {
		this.newSeller = newSeller;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getTransUser() {
		return transUser;
	}

	public void setTransUser(Employee transUser) {
		this.transUser = transUser;
	}

	@Override
	public String toString() {
		return "CutomerTransfer [id=" + id + ", transTime=" + transTime + ", transReason=" + transReason
				+ ", oldSeller=" + oldSeller + ", newSeller=" + newSeller + ", customer=" + customer + ", transUser="
				+ transUser + "]";
	}

}
