package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Pie;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerQuery;

public interface CustomerMapper {

	void save(Customer t);

	void update(Customer t);

	void delete(Long id);

	Customer getOne(Long id);

	List<Customer> getAll();

	List<Customer> queryPage(CustomerQuery query);

	Long queryCount(CustomerQuery query);

	//获取客户来源
	List<SystemDictionaryItem> getCustomerSource();

	//获取客户营销人员
	List<Employee> getCustomerSeller();

	//获取客户职业
	List<SystemDictionaryItem> getCustomerJob();

	//获取客户收入阶梯
	List<SystemDictionaryItem> getCustomerSalaryLevel();
	
	//根据姓名获取客户
	Customer getCustomerByName(String name);

}
