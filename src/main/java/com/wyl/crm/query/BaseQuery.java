package com.wyl.crm.query;

import org.springframework.util.StringUtils;

import com.wyl.crm.utils.UserContext;

/**
 * 查询对象的基类
 * 
 * @author Administrator
 *
 */
public class BaseQuery {

	public BaseQuery() {
		// 初始化当前用户角色
		this.managerRole = UserContext.isRole("admin,manager,boss,marketManager");
	}

	public boolean managerRole = false;// 默认不为管理员权限

	public boolean isManagerRole() {
		return managerRole;
	}

	public void setManagerRole(boolean managerRole) {
		this.managerRole = managerRole;
	}
	
	public Long getCurrentUserId(){
		return UserContext.getUser().getId();
	}

	private String searchKey;// 关键字
	private Integer status;// 关键字

	// 分页
	private Integer currentPage = 1;// 当前页码
	private Integer pageSize = 10;// 每页长度,如果值为-1，则查询所有（不分页）

	public Integer getStart() {// 起始位置
		return (currentPage - 1) * pageSize;
	}

	public Integer getEnd() {
		return this.pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKey() {
		if (StringUtils.hasLength(searchKey)) {
			return "%" + searchKey + "%";
		}
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BaseQuery [searchKey=" + searchKey + ", currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}

	// ============================EasyUI分页兼容==========================================
	public void setPage(int page) { // 档期页
		this.currentPage = page;
	}

	public void setRows(int rows) {
		this.pageSize = rows;
	}
}
