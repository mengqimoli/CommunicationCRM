package com.wyl.crm.domain;

import java.io.Serializable;

public class Pie implements Serializable {
	
	private Integer status;

	private Long count;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Pie [status=" + status + ", count=" + count + "]";
	}

}
