package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.CustomerTransfer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.query.CustomerTransferQuery;

public interface CustomerTransferMapper{
	void save(CustomerTransfer t);

	void update(CustomerTransfer t);

	void delete(Long id);

	CustomerTransfer getOne(Long id);

	List<CustomerTransfer> getAll();

	List<CustomerTransfer> queryPage(CustomerTransferQuery query);

	Long queryCount(CustomerTransferQuery query);
	
	List<Employee> getEmployee();
	
}
