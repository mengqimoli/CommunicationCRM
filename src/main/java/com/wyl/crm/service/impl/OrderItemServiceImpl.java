package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.OrderItem;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.OrderItemMapper;
import com.wyl.crm.mapper.OrderMapper;
import com.wyl.crm.query.OrderItemQuery;
import com.wyl.crm.service.IOrderItemService;
import com.wyl.crm.utils.PageResult;

/*
 * 合同扩展类
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService {
	@Autowired
	private OrderItemMapper mapper;
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void save(OrderItem orderItem) {
		mapper.save(orderItem);
	}

	@Override
	public void update(OrderItem orderItem) {
		mapper.update(orderItem);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public PageResult<OrderItem> queryPage(OrderItemQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<OrderItem> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	// 保存合同明细
	/*
	 * @Override public void saveItem(Order order) { // 拿到合同明细 List<OrderItem>
	 * orderItems = order.getOrderItem(); for (OrderItem orderItem : orderItems)
	 * { // 合同金额所占比例 String scale = orderItem.getMoney().divide(order.getSum(),
	 * 2, RoundingMode.HALF_UP) .multiply(new BigDecimal("100")).toString() +
	 * "%"; orderItem.setScale(scale); orderItem.setOrder(order);
	 * mapper.save(orderItem); } }
	 */
	// 计算比例的测试
	/*
	 * public static void main(String[] args) { BigDecimal a = new
	 * BigDecimal("1000"); BigDecimal b = new BigDecimal("2000"); String
	 * residual = a.divide(b).multiply(new BigDecimal("100")).toString()+"%";
	 * System.out.println(residual); }
	 */

	@Override
	public List<OrderItem> getItem(Long id) {
		return mapper.getItem(id);
	}

	@Override
	public void deleteByOrderId(Long id) {
		mapper.deleteByOrderId(id);
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
