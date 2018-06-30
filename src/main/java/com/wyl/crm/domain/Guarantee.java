package com.wyl.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 保修单
 * 
 * @author Jasmine
 *
 */
public class Guarantee implements Serializable {
	private Long id; // id
	private String sn; // 保修单编号
	private Customer customer;// 保修客户
	private Date expireTime = new Date();// 质保到期时间
	private Integer status = 1;// 状态(0失效保修单 1生效保修单)
	List<GuaranteeItem> guaranteeItem = new ArrayList<>();// 保修单明细

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getExpireTime() {
		return expireTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GuaranteeItem> getGuaranteeItem() {
		return guaranteeItem;
	}

	public void setGuaranteeItem(List<GuaranteeItem> guaranteeItem) {
		this.guaranteeItem = guaranteeItem;
	}

	@Override
	public String toString() {
		return "Guarantee [id=" + id + ", sn=" + sn + ", customer=" + customer + ", expireTime=" + expireTime
				+ ", status=" + status + "]";
	}

}
