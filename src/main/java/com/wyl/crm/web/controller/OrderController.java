package com.wyl.crm.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.domain.Order;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.OrderQuery;
import com.wyl.crm.service.IContractItemService;
import com.wyl.crm.service.IContractService;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.service.IGuaranteeService;
import com.wyl.crm.service.IOrderService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.MakeOrderNum;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/order")
@Resource("定金订单管理")
public class OrderController {

	@Autowired
	private IOrderService orderServicce;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IContractService constractService;
	@Autowired
	private IContractItemService contractItemService;
	@Autowired
	private IGuaranteeService guaranteeService;

	@RequestMapping("/index")
	@Resource("定金订单页面导向")
	public String index() {
		return "order/order";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("定金订单列表")
	public PageResult<Order> list(OrderQuery query) {
		return orderServicce.queryPage(query);
	}

	@RequestMapping("/getSeller")
	@ResponseBody
	public List<Employee> getSeller() {
		return orderServicce.getAllSeller();
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("定金订单删除")
	public AjaxResult delete(Long id) {
		try {
			orderServicce.delete(id);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}
	}

	/* /order/save */
	@RequestMapping("/save")
	@ResponseBody
	@Resource("定金订单保存/修改")
	public AjaxResult save(Order order) {
		if (order != null && order.getId() != null) {
			try {
				orderServicce.update(order);
				return new AjaxResult();
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxResult(e.getMessage());
			}
		} else {
			try {
				order.setSn("DJ" + MakeOrderNum.getRandomFileName());
				order.setStatus(-1);
				orderServicce.save(order);
				// 设为定金客户
				Customer customer = customerService.getOne(order.getCustomer().getId());
				customer.setCustomerStatus(new SystemDictionaryItem(23L));
				customerService.update(customer);
				return new AjaxResult();
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxResult(e.getMessage());
			}
		}
	}

	@RequestMapping("/toContract")
	@ResponseBody
	@Resource("定金订单生成合同")
	public AjaxResult toContract(Order order) {
		try {
			// 修改订单状态
			order.setStatus(1);
			orderServicce.update(order);
			// 设为合同客户
			Customer customer = customerService.getOne(order.getCustomer().getId());
			customer.setCustomerStatus(new SystemDictionaryItem(24L));
			customerService.update(customer);
			// 保存合同
			Contract contract = new Contract();
			contract.setSn("HT" + MakeOrderNum.getRandomFileName());
			contract.setCustomer(order.getCustomer());
			contract.setSum(order.getTotalAmount());
			contract.setSignTime(new Date());
			contract.setSeller(order.getSeller());
			contract.setStatus(1);
			constractService.save(contract);
			// 生成合同明细
			ContractItem contractItem = new ContractItem();
			contractItem.setMoney(order.getSum());
			contractItem.setScale(contractItem.getMoney().divide(order.getTotalAmount(), 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal("100")).toString() + "%");
			contractItem.setContract(contract);
			contractItem.setPayTime(new Date());
			contractItem.setStatus(1);
			contractItemService.save(contractItem);
			// 生成保修单
			Guarantee guarantee = new Guarantee();
			guarantee.setSn("BX" + MakeOrderNum.getRandomFileName());
			guarantee.setCustomer(order.getCustomer());

			Calendar calendar = Calendar.getInstance();
			Date date = new Date(System.currentTimeMillis());
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, +2);
			date = calendar.getTime();
			System.out.println(date);
			
			guarantee.setExpireTime(date);
			guarantee.setStatus(1);
			guaranteeService.save(guarantee);

			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("定金订单生成合同失败，原因是：" + e.getMessage());
		}
	}

	// 设备类型
	@RequestMapping("/deviceType")
	@ResponseBody
	public List<SystemDictionaryItem> deviceType() {
		return orderServicce.getDeviceType();
	}

	// 设备型号
	@RequestMapping("/unitType")
	@ResponseBody
	public List<SystemDictionaryItem> unitType() {
		return orderServicce.getUnitType();
	}

}
