package com.wyl.crm.query;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 分页条件
 * 
 * @author Administrator
 *
 */
public class ContractQuery extends BaseQuery {
	private String sn;
	private String sum;

	private Long customerId;
	private Long sellerId;

	private Date beginTime;

	private Date endTime;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBeginTime() {
		return beginTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		if (sn != null && !"".equals(sn)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long zz = new Long(sn);
			String xx = simpleDateFormat.format(zz);
			this.sn = xx;
		} else {
			this.sn = sn;
		}
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

}
