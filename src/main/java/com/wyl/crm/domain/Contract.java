package com.wyl.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 合同
 *
 */
public class Contract implements Serializable {
	private Long id; // id
	private String sn; // 合同单号
	private Customer customer;// 合同客户
	private Date signTime = new Date();// 签订时间
	private BigDecimal sum;// 合同金额
	private Employee seller;// 营销人员
	private Integer status = 1;// 状态(-1合同已失效 1合同已生效)

	List<ContractItem> contractItem = new ArrayList<>();// 合同明细

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getSignTime() {
		return signTime;
	}

	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

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

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ContractItem> getContractItem() {
		return contractItem;
	}

	public void setContractItem(List<ContractItem> contractItem) {
		this.contractItem = contractItem;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", sn=" + sn + ", customer=" + customer + ", signTime=" + signTime + ", sum="
				+ sum + ", seller=" + seller + ", status=" + status + ", contractItem=" + contractItem + "]";
	}

}
