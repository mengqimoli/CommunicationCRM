package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.CustomerDevPlan;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerDevPlanQuery;
import com.wyl.crm.utils.PageResult;

public interface ICustomerDevPlanService {
	void save(CustomerDevPlan obj);

	void delete(Long id);

	void update(CustomerDevPlan obj);

	CustomerDevPlan getOne(Long id);

	// 查询所有
	List<CustomerDevPlan> getAll();

	// 分页集合查询
	PageResult<CustomerDevPlan> queryPage(CustomerDevPlanQuery query);

	//实施方式
	List<SystemDictionaryItem> getPlanType();

	List<Employee> getInputUser();

	void deleteByPote(String name);

}
