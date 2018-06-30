package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.domain.GuaranteeItem;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.GuaranteeItemQuery;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.service.IGuaranteeItemService;
import com.wyl.crm.service.IGuaranteeService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/guaranteeItem")
@Resource("保修单明细管理")
public class GuaranteeItemController {

	@Autowired
	private IGuaranteeItemService guaranteeItemService;
	@Autowired
	private IGuaranteeService guaranteeService;
	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/list")
	@ResponseBody
	@Resource("保修单明细列表")
	public PageResult<GuaranteeItem> list(GuaranteeItemQuery query) {
		return guaranteeItemService.queryPage(query);
	}

	@RequestMapping("/getItem")
	@ResponseBody
	public List<GuaranteeItem> getItem(Long id) {
		return guaranteeItemService.getItem(id);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("保修单明细删除")
	public AjaxResult delete(Long id) {
		try {
			guaranteeItemService.delete(id);
			return new AjaxResult("该保修单明细删除成功！！！");
		} catch (LogicException e) {
			return new AjaxResult(e.getMessage(), e.getErrorCode());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Resource("保修单明细保存/修改")
	public AjaxResult save(GuaranteeItem guaranteeItem) {
		if (guaranteeItem != null && guaranteeItem.getId() != null) {
			try {
				guaranteeItemService.update(guaranteeItem);
				return new AjaxResult("该保修单明细修改成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该保修单明细修改失败！！！", -101);
			}
		} else {
			try {
				System.out.println("ss");
				// 设为保修客户guaranteeItem.getGuarantee().getCustomer().getName()
				Long id = guaranteeItem.getGuarantee().getId();
				Guarantee one = guaranteeService.getOne(id);
				Customer customer = customerService.getOne(one.getId());
				customer.setCustomerStatus(new SystemDictionaryItem(25L));
				customerService.update(customer);
				guaranteeItemService.save(guaranteeItem);
				return new AjaxResult("该保修单明细保存成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该保修单明细保存失败！！！", -101);
			}
		}
	}

	@RequestMapping("/getRepairer")
	@ResponseBody
	public List<Employee> getRepairer() {
		return guaranteeItemService.getRepairer();
	}
}
