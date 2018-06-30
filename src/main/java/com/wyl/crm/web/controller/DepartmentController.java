package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Department;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.DepartmentQuery;
import com.wyl.crm.service.IDepartmentService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/department")
@Resource("部门管理")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 页面导向方法
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@Resource("部门页面导向")
	public String index() {
		return "department/department";
		// return null; // 默认： 前缀 + 请求路径 + 后缀
	}

	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	@Resource("部门列表")
	public PageResult<Department> list(DepartmentQuery qo) {
		// System.out.println(qo);
		// 声明
		PageResult<Department> pr = null;
		// 封装
		pr = departmentService.queryPage(qo);
		// 返回
		return pr;
	}
	/*
	 * public List<Department> list(DepartmentQuery qo){ //
	 * System.out.println(qo); // 声明 List<Department> list = null; // 封装 list =
	 * deptService.queryList(qo); // 返回 return list; }
	 */ /*
		 * public List<Department> list(){ // 声明 List<Department> list = null;
		 * // 封装 list = deptService.listALL(); // 返回 return list; }
		 */

	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@Resource("部门保存/修改")
	public AjaxResult save(Department department) {
		System.out.println("save" + department);
		// 声明
		AjaxResult ar = null;
		// 封装
		if (department.getId() == null) {
			departmentService.save(department);
			ar = new AjaxResult("部门保存成功！");
		} else {
			departmentService.update(department);
			ar = new AjaxResult("部门修改成功！");
		}
		// 返回
		return ar;
	}

	/**
	 * 停用
	 * 
	 * @return
	 */
	@RequestMapping("/stopUse")
	@ResponseBody
	@Resource("部门停用")
	public AjaxResult stopUse(Long id) {
		System.out.println("/stopUse....");
		AjaxResult ar = null;
		try {
			departmentService.stopUse(id);
			ar = new AjaxResult("部门停用成功！");
		} catch (LogicException e) {
			ar = new AjaxResult("员工离职失败:" + e.getMessage(), e.getErrorCode());
		}

		return ar;
	}

	/**
	 * 启用
	 * 
	 * @return
	 */
	@RequestMapping("/startUse")
	@ResponseBody
	@Resource("部门启用")
	public AjaxResult startUse(Long id) {
		System.out.println("/startUse....");
		AjaxResult ar = null;

		departmentService.startUse(id);

		ar = new AjaxResult("部门启用成功！");
		return ar;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Resource("部门删除")
	public AjaxResult delete(Long id) {
		System.out.println("/delete....");
		AjaxResult ar = null;
		departmentService.delete(id);
		ar = new AjaxResult("部门删除成功！");
		return ar;
	}

	/**
	 * 获取部门树
	 * 
	 * @return
	 */
	@RequestMapping("/getDepartmentTree")
	@ResponseBody
	public List<Department> getDepartmentTree() {
		System.out.println("/getDepartmentTree....");
		// 声明
		List<Department> list = null;
		// 封装
		list = departmentService.getDepartmentTree();
		// 返回
		return list;
	}
}
