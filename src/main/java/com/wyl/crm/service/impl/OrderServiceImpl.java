package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Order;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.OrderMapper;
import com.wyl.crm.query.OrderQuery;
import com.wyl.crm.service.IOrderService;
import com.wyl.crm.utils.PageResult;

/**
 * 合同订单的service实现 扩展用
 * 
 * @author 63252
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper mapper;

	@Override
	public void save(Order obj) {
		mapper.save(obj);
	}

	@Override
	public void update(Order obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public Order getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<Order> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<Order> queryPage(OrderQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Order> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<Employee> getAllSeller() {
		// 返回所有员工的信息,可以选择销售员
		return mapper.getAllSeller();
	}

	@Override
	public List<SystemDictionaryItem> getDeviceType() {
		return mapper.getDeviceType();
	}

	@Override
	public List<SystemDictionaryItem> getUnitType() {
		return mapper.getUnitType();
	}
}
