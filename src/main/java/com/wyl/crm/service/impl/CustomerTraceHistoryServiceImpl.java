package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.CustomerTraceHistory;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.CustomerTraceHistoryMapper;
import com.wyl.crm.query.CustomerTraceHistoryQuery;
import com.wyl.crm.service.ICustomerTraceHistoryService;
import com.wyl.crm.utils.PageResult;

@Service
public class CustomerTraceHistoryServiceImpl implements ICustomerTraceHistoryService {

	@Autowired
	private CustomerTraceHistoryMapper mapper;

	@Override
	public void save(CustomerTraceHistory obj) {
		mapper.save(obj);
	}

	@Override
	public void update(CustomerTraceHistory obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public CustomerTraceHistory getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<CustomerTraceHistory> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<CustomerTraceHistory> queryPage(CustomerTraceHistoryQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<CustomerTraceHistory> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<SystemDictionaryItem> getCustomerSource() {
		return mapper.getCustomerSource();
	}

	@Override
	public List<Employee> getCustomerSeller() {
		// 查询营销人员
		return mapper.getCustomerSeller();
	}

}
