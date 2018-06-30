package com.wyl.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 定金单
 *
 */
public class Order implements Serializable {
	private Long id; // id
	private String sn; // 定单编号
	private Customer customer;// 定金客户
	private Date signTime = new Date(); // 签订时间
	private BigDecimal sum; // 定金金额
	private BigDecimal totalAmount; // 总金额
	private Employee seller;// 营销人员
	private String intro; // 摘要/备注
	private Integer status = -1;// 状态(-1未生成合同 1已生成合同)

	private SystemDictionaryItem deviceType; // 设备(29-36)
	private SystemDictionaryItem unitType; // 设备型号(37-39)

	public SystemDictionaryItem getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(SystemDictionaryItem deviceType) {
		this.deviceType = deviceType;
	}

	public SystemDictionaryItem getUnitType() {
		return unitType;
	}

	public void setUnitType(SystemDictionaryItem unitType) {
		this.unitType = unitType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getSignTime() {
		return signTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", sn=" + sn + ", customer=" + customer + ", signTime=" + signTime + ", seller="
				+ seller + ", sum=" + sum + ", intro=" + intro + ", status=" + status + "]";
	}

}
