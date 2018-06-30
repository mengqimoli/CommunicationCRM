package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.CustomerTransfer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.mapper.CustomerTransferMapper;
import com.wyl.crm.query.CustomerTransferQuery;
import com.wyl.crm.service.ICustomerTransferService;
import com.wyl.crm.utils.PageResult;

@Service
public class CustomerTransferServiceImpl implements ICustomerTransferService {

	@Autowired
	private CustomerTransferMapper mapper;

	@Override
	public void save(CustomerTransfer obj) {
		mapper.save(obj);
	}

	@Override
	public void update(CustomerTransfer obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public CustomerTransfer getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<CustomerTransfer> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<CustomerTransfer> queryPage(CustomerTransferQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<CustomerTransfer> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<Employee> getEmployee() {

		return mapper.getEmployee();
	}

}
