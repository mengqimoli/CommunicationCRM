package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.CustomerDevPlan;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerDevPlanQuery;

public interface CustomerDevPlanMapper {
	void save(CustomerDevPlan t);

	void update(CustomerDevPlan t);

	void delete(Long id);

	CustomerDevPlan getOne(Long id);

	List<CustomerDevPlan> getAll();

	List<CustomerDevPlan> queryPage(CustomerDevPlanQuery query);

	Long queryCount(CustomerDevPlanQuery query);

	//实施方式
	List<SystemDictionaryItem> getPlanType();

	//获取录入人员
	List<Employee> getInputUser();

	void deleteByPoteId(Long id);

}
