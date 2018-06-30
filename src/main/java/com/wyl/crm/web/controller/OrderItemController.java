package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.OrderItem;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.OrderItemQuery;
import com.wyl.crm.service.IOrderItemService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/orderItem")
@Resource("定金订单明细管理")
public class OrderItemController {

	@Autowired
	private IOrderItemService orderItemService;

	@RequestMapping("/list")
	@ResponseBody
	@Resource("定金订单明细列表")
	public PageResult<OrderItem> list(OrderItemQuery query) {
		return orderItemService.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("定金订单明细删除")
	public AjaxResult delete(Long id) {
		try {
			orderItemService.delete(id);
			return new AjaxResult("该定金订单明细删除成功！！！");
		} catch (LogicException e) {
			return new AjaxResult(e.getMessage(), e.getErrorCode());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Resource("定金订单明细保存/修改")
	public AjaxResult save(OrderItem orderItem) {
		if (orderItem != null && orderItem.getId() != null) {
			try {
				orderItemService.update(orderItem);
				return new AjaxResult("该定金订单明细修改成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该定金订单明细修改失败！！！", -101);
			}
		} else {
			try {
				orderItemService.save(orderItem);
				return new AjaxResult("该定金订单明细保存成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该定金订单明细保存失败！！！", -101);
			}
		}
	}

	@RequestMapping("/getItem")
	@ResponseBody
	public List<OrderItem> getItem(Long id) {
		return orderItemService.getItem(id);
	}

	// 设备类型
	@RequestMapping("/deviceType")
	@ResponseBody
	public List<SystemDictionaryItem> deviceType() {
		return orderItemService.getDeviceType();
	}

	// 设备型号
	@RequestMapping("/unitType")
	@ResponseBody
	public List<SystemDictionaryItem> unitType() {
		return orderItemService.getUnitType();
	}
}
