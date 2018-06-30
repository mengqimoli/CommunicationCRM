package com.wyl.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 合同明细
 *
 */
public class ContractItem implements Serializable {
	private Long id; // id
	private Contract contract;// 所属合同(多个明细对应一个合同)
	private BigDecimal money;// 付款金额(已交金额,默认为定金)
	private String scale;// 所占比例
	private Date payTime = new Date();// 付款时间
	private String intro;// 摘要/备注
	private Integer status;// 状态(-1未付款1已付款)

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getPayTime() {
		return payTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
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
		return "ContractItem [id=" + id + ", contract=" + contract + ", money=" + money + ", scale=" + scale
				+ ", payTime=" + payTime + ", intro=" + intro + ", status=" + status + "]";
	}

}
