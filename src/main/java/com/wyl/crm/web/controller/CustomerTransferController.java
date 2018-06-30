package com.wyl.crm.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.CustomerTransfer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.query.CustomerTransferQuery;
import com.wyl.crm.service.ICustomerTransferService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;
import com.wyl.crm.utils.UserContext;

@Controller
@RequestMapping("/customerTransfer")
@Resource("客户移交记录管理")
public class CustomerTransferController {

	@Autowired
	private ICustomerTransferService customerTransferServiceImpl;

	@Resource("客户移交记录页面导向")
	@RequestMapping("/index")
	public String index() {
		return "customerTransfer/customerTransfer";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("客户移交记录列表")
	public PageResult<CustomerTransfer> list(CustomerTransferQuery query) {
		return customerTransferServiceImpl.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("客户移交记录删除")
	public AjaxResult delete(Long id) {
		try {
			customerTransferServiceImpl.delete(id);
			return new AjaxResult("客户移交记录删除成功!!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("客户移交记录删除失败!!!", -101);
		}

	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("客户移交记录保存/修改")
	public AjaxResult save(CustomerTransfer customerTransfer,Long id) {
		System.out.println(id+"..........////////");
		
		if (customerTransfer != null && customerTransfer.getId() != null) {
			try {
				customerTransfer.setTransUser(UserContext.getUser());
				customerTransferServiceImpl.update(customerTransfer);
				return new AjaxResult("客户移交记录修改成功!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("客户移交记录修改失败!!!", -101);
			}
		} else {
			try {
				// 录入时间为当前系统事件
				customerTransfer.setTransTime(new Date());
				customerTransferServiceImpl.save(customerTransfer);
				return new AjaxResult("客户移交记录保存成功!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("客户移交记录保存失败!!!", -101);
			}
		}

	}

	@RequestMapping("/transfer")
	@ResponseBody
	public List<Employee> getTraceUser() {
		return customerTransferServiceImpl.getEmployee();
	}

}
