package com.wyl.crm.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.CustomerDevPlan;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerDevPlanQuery;
import com.wyl.crm.service.ICustomerDevPlanService;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.service.IPotentialCustomerService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;
import com.wyl.crm.utils.UserContext;

@Controller
@RequestMapping("/customerDevPlan")
@Resource("潜在客户开发计划管理")
public class CustomerDevPlanController {

	@Autowired
	private ICustomerDevPlanService customerDevPlanImpl;
	@Autowired
	private IPotentialCustomerService potentialCustomerService;

	@Resource("潜在客户开发计划页面导向")
	@RequestMapping("/index")
	public String index() {
		return "customerDevPlan/customerDevPlan";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("潜在客户开发计划列表")
	public PageResult<CustomerDevPlan> list(CustomerDevPlanQuery query) {
		return customerDevPlanImpl.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("潜在客户开发计划删除")
	public AjaxResult delete(Long id) {

		try {
			customerDevPlanImpl.delete(id);
			return new AjaxResult("潜在客户开发计划删除成功!!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("潜在客户开发计划删除成功!!!", -101);
		}

	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("潜在客户开发计划保存/修改")
	public AjaxResult save(CustomerDevPlan customerDevPlan) {
		if (customerDevPlan != null && customerDevPlan.getId() != null) {
			try {
				customerDevPlan.setInputUser(UserContext.getUser());
				Long id = customerDevPlan.getPote().getId();
				PotentialCustomer potentialCustomer = potentialCustomerService.getOne(id);
				customerDevPlan.setSeller(potentialCustomer.getSeller());
				customerDevPlanImpl.update(customerDevPlan);
				return new AjaxResult("潜在客户开发计划修改成功!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("潜在客户开发计划修改成功!!!", -101);
			}
		} else {
			try {
				/*Long id = customerDevPlan.getPote().getId();
				potentialCustomerService.delete(id);*/
				// 录入时间为当前系统事件
				customerDevPlan.setInputUser(UserContext.getUser());
				customerDevPlan.setInputTime(new Date());
				
			
				Long id = customerDevPlan.getPote().getId();
				PotentialCustomer potentialCustomer = potentialCustomerService.getOne(id);
				customerDevPlan.setSeller(potentialCustomer.getSeller());
				
				customerDevPlanImpl.save(customerDevPlan);
				return new AjaxResult("潜在客户开发计划保存成功!!!");
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxResult("潜在客户开发计划保存成功!!!", -101);
			}
		}
	}


	@RequestMapping("/planType")
	@ResponseBody
	public List<SystemDictionaryItem> getPlanType() {
		return customerDevPlanImpl.getPlanType();
	}

	@RequestMapping("/inputUser")
	@ResponseBody
	public List<Employee> getInputUser() {
		return customerDevPlanImpl.getInputUser();
	}

}
