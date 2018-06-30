package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.CustomerDevPlan;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.CustomerDevPlanMapper;
import com.wyl.crm.mapper.PotentialCustomerMapper;
import com.wyl.crm.query.CustomerDevPlanQuery;
import com.wyl.crm.service.ICustomerDevPlanService;
import com.wyl.crm.utils.PageResult;

@Service
public class CustomerDevPlanServiceImpl implements ICustomerDevPlanService {

	@Autowired
	private CustomerDevPlanMapper mapper;
	@Autowired
	private PotentialCustomerMapper potentialCustomerMapper;

	@Override
	public void save(CustomerDevPlan obj) {
		mapper.save(obj);
	}

	@Override
	public void update(CustomerDevPlan obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public CustomerDevPlan getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<CustomerDevPlan> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<CustomerDevPlan> queryPage(CustomerDevPlanQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<CustomerDevPlan> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<SystemDictionaryItem> getPlanType() {
		return mapper.getPlanType();
	}

	@Override
	public List<Employee> getInputUser() {
		// 查询营销人员
		return mapper.getInputUser();
	}

	@Override
	public void deleteByPote(String poteName) {
		PotentialCustomer potentialCustomer = potentialCustomerMapper.getByPoteName(poteName);
		Long id = potentialCustomer.getId();
		mapper.deleteByPoteId(id);
	}

}
