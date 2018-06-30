package com.wyl.crm.query;

public class CustomerQuery extends BaseQuery {
	private String sellerName;

	private Integer pool;

	private boolean sex;

	private Long salaryId;

	private Long jobId;

	private Long sourceId;

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Integer getPool() {
		return pool;
	}

	public void setPool(Integer pool) {
		this.pool = pool;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

}
