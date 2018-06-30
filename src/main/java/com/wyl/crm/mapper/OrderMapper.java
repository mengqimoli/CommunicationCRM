package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Order;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.OrderQuery;

public interface OrderMapper {
	void save(Order t);

	void update(Order t);

	void delete(Long id);

	Order getOne(Long id);

	List<Order> getAll();

	List<Order> queryPage(OrderQuery query);

	Long queryCount(OrderQuery query);

	List<Employee> getAllSeller();

	List<SystemDictionaryItem> getDeviceType();

	List<SystemDictionaryItem> getUnitType();
}
