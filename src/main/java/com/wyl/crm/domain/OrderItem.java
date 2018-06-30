package com.wyl.crm.domain;

import java.io.Serializable;

/**
 * @author 合同明细
 *
 */
public class OrderItem implements Serializable {
	private Long id; // id
	private Order order;// 所属订单(多个明细对应一个订单)
	private Integer number;// 数量
	private String intro;// 摘要/备注
	private Integer status;// 状态(-1未付款1已付款)

	private SystemDictionaryItem deviceType; // 设备(29-36)
	private SystemDictionaryItem unitType; // 设备型号(37-39)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

}
