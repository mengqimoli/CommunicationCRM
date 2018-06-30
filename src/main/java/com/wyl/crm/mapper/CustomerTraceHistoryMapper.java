package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.CustomerTraceHistory;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerTraceHistoryQuery;

public interface CustomerTraceHistoryMapper {

	void save(CustomerTraceHistory t);

	void update(CustomerTraceHistory t);

	void delete(Long id);

	CustomerTraceHistory getOne(Long id);

	List<CustomerTraceHistory> getAll();

	List<CustomerTraceHistory> queryPage(CustomerTraceHistoryQuery query);

	Long queryCount(CustomerTraceHistoryQuery query);

	// 获取客户来源
	List<SystemDictionaryItem> getCustomerSource();

	// 获取营销人员
	List<Employee> getCustomerSeller();

}
