package com.wyl.crm.utils;

import java.util.List;

/**
 * 分页对象
 * 
 * @author admin
 *
 * @param <T>
 */
public class PageResult<T> {

	private Long total; // 符合条件的总记录数

	private List<T> rows; // 当前页的记录数

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}

}
