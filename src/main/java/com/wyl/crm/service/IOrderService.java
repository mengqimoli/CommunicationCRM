package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Order;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.OrderQuery;
import com.wyl.crm.utils.PageResult;

/**
 * order的service层 用于扩展
 * 
 * @author 63252
 *
 */
public interface IOrderService {

	void save(Order obj);

	void delete(Long id);

	void update(Order obj);

	Order getOne(Long id);

	// 查询所有
	List<Order> getAll();

	// 分页集合查询
	PageResult<Order> queryPage(OrderQuery query);

	List<Employee> getAllSeller();

	//设备类型
	List<SystemDictionaryItem> getDeviceType();

	//设备型号
	List<SystemDictionaryItem> getUnitType();

}
