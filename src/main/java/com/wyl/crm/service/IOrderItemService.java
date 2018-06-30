package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.OrderItem;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.OrderItemQuery;
import com.wyl.crm.utils.PageResult;

public interface IOrderItemService {

	void save(OrderItem obj);

	void delete(Long id);

	void update(OrderItem obj);

	// 分页集合查询
	PageResult<OrderItem> queryPage(OrderItemQuery query);

	// 获取合同明细
	List<OrderItem> getItem(Long id);

	void deleteByOrderId(Long id);

	// 设备类型
	List<SystemDictionaryItem> getDeviceType();

	// 设备型号
	List<SystemDictionaryItem> getUnitType();

}
