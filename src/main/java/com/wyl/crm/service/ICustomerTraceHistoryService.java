package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.CustomerTraceHistory;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerTraceHistoryQuery;
import com.wyl.crm.utils.PageResult;

public interface ICustomerTraceHistoryService {

	void save(CustomerTraceHistory obj);

	void delete(Long id);

	void update(CustomerTraceHistory obj);

	CustomerTraceHistory getOne(Long id);

	// 查询所有
	List<CustomerTraceHistory> getAll();

	// 分页集合查询
	PageResult<CustomerTraceHistory> queryPage(CustomerTraceHistoryQuery query);

	// 获取客户来源
	List<SystemDictionaryItem> getCustomerSource();

	// 获取营销人员
	List<Employee> getCustomerSeller();

}
