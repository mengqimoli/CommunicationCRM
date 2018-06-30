package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.CustomerTransfer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.query.CustomerTransferQuery;
import com.wyl.crm.utils.PageResult;

public interface ICustomerTransferService {
	void save(CustomerTransfer obj);

	void delete(Long id);

	void update(CustomerTransfer obj);

	CustomerTransfer getOne(Long id);

	// 查询所有
	List<CustomerTransfer> getAll();

	// 分页集合查询
	PageResult<CustomerTransfer> queryPage(CustomerTransferQuery query);

	List<Employee> getEmployee();
}
