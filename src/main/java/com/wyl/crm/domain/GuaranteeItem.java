package com.wyl.crm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 保修单明细
 * 
 * @author Jasmine
 *
 */
public class GuaranteeItem implements Serializable {
	private Long id; // id
	private Guarantee guarantee;// 所属保修单(多个明细对应一个保修单)
	private Date repairTime = new Date();// 保修时间
	private Employee employee;// 保修员工
	private String intro;// 摘要/备注
	private Integer status;// 状态(0没解决1已解决)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guarantee getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Guarantee guarantee) {
		this.guarantee = guarantee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getRepairTime() {
		return repairTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
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
		return "GuaranteeItem [id=" + id + ", guarantee=" + guarantee + ", repairTime=" + repairTime + ", intro="
				+ intro + ", status=" + status + "]";
	}

}
