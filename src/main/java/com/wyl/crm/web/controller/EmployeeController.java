package com.wyl.crm.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.EmployeeQuery;
import com.wyl.crm.service.IEmployeeService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.EncryptUtil;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/employee")
@Resource("员工管理")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/index")
	@Resource("员工页面导向")
	public String index() {
		return "employee/employee";
	}

	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	@Resource("员工列表")
	public PageResult list(EmployeeQuery qo) {
		PageResult<Employee> pr = null;
		pr = employeeService.queryPage(qo);
		return pr;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@Resource("员工保存/修改")
	public AjaxResult save(Employee employee) {
		// 准备返回对象
		AjaxResult ar = null;
		try {
			if (employee.getId() == null) {
				// 调用业务逻辑
				employee.setInputTime(new Date());
				employee.setStatus(1);
				employee.setPassword(EncryptUtil.encryptPassword("000000"));
				employeeService.save(employee);
				// 封装返回结果
				ar = new AjaxResult("员工保存成功！");
			} else {
				// 调用业务逻辑
				employeeService.update(employee);
				// 封装返回结果
				ar = new AjaxResult("员工修改成功！");
			}

		} catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("员工保存失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	/**
	 * 离职
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/stopUse")
	@ResponseBody
	@Resource("员工离职")
	public AjaxResult stopUse(Long id) {
		AjaxResult ar = null;
		try {
			employeeService.stopUse(id);
			ar = new AjaxResult("员工离职成功！");
		} catch (LogicException e) {
			ar = new AjaxResult("员工离职失败:" + e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

	/**
	 * 复职
	 * 
	 * @return
	 */
	@RequestMapping("/startUse")
	@ResponseBody
	@Resource("员工复职")
	public AjaxResult startUse(Long id) {
		System.out.println("/startUse....");
		AjaxResult ar = null;

		employeeService.startUse(id);

		ar = new AjaxResult("员工复职成功！");
		return ar;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Resource("员工删除")
	public AjaxResult delete(Long id) {
		System.out.println("/delete....");
		AjaxResult ar;
		try {
			employeeService.delete(id);
			ar = new AjaxResult("员工删除成功！");
		} catch (LogicException e) {
			ar = new AjaxResult("员工删除失败!" + e.getMessage(), -100);
		}
		return ar;
	}
}
