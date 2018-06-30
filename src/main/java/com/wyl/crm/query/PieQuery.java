package com.wyl.crm.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PieQuery extends BaseQuery {

	private Date inputTimeStart;
	private Date inputTimeEnd;

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getInputTimeStart() {
		return inputTimeStart;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setInputTimeStart(Date inputTimeStart) {
		this.inputTimeStart = inputTimeStart;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getInputTimeEnd() {
		return inputTimeEnd;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setInputTimeEnd(Date inputTimeEnd) {
		this.inputTimeEnd = inputTimeEnd;
	}

}
