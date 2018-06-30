package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.OrderItem;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.OrderItemQuery;

public interface OrderItemMapper {

	void save(OrderItem t);

	void update(OrderItem t);

	void delete(Long id);

	List<OrderItem> queryPage(OrderItemQuery query);

	Long queryCount(OrderItemQuery query);

	// 根据合同获取明细
	List<OrderItem> getItem(Long id);

	// 删除订单的时候删除明细
	void deleteByOrderId(Long id);

	List<SystemDictionaryItem> getDeviceType();

	List<SystemDictionaryItem> getUnitType();

}
