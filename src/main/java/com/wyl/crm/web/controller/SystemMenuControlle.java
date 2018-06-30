package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.SystemMenuQuery;
import com.wyl.crm.service.ISystemMenuService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;
import com.wyl.crm.utils.UserContext;

@Controller
@RequestMapping("/systemMenu")
@Resource("系统菜单管理")
public class SystemMenuControlle {
	@Autowired
	private ISystemMenuService service;

	@Resource("系统菜单页面导向")
	@RequestMapping("/index")
	public String index() {
		return "/systemMenu/systemMenu";
	}

	@Resource("系统菜单列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult<SystemMenu> list(SystemMenuQuery query) {
		return service.queryPage(query);
	}

	@Resource("系统菜单保存/修改")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemMenu systemMenu) {
		// 准备返回对象
		AjaxResult ar;
		try {
			if (systemMenu.getId() == null) {
				service.save(systemMenu);
				ar = new AjaxResult("菜单保存成功！！");
			} else {
				service.update(systemMenu);
				ar = new AjaxResult("菜单修改成功！！");
			}

		} catch (LogicException e) {
			ar = new AjaxResult("菜单保存失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@Resource("系统菜单删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar;
		try {
			service.delete(id);
			ar = new AjaxResult("菜单删除成功！！");
		} catch (LogicException e) {
			ar = new AjaxResult("菜单删除失败:" + e.getMessage(), e.getErrorCode());
		}

		return ar;
	}

	@RequestMapping("/getSystemMenuByEmpId")
	@ResponseBody
	public List<SystemMenu> getSystemMenuByEmpId() {
		Long EmpId = UserContext.getUser().getId();
		List<SystemMenu> list = service.getSystemMenuByEmpId(EmpId);
		System.out.println("getSystemMenuByEmpId................." + list.size());
		return list;
	}

	@RequestMapping("/getSystemMenuTree")
	@ResponseBody
	public List<SystemMenu> getSystemMenuTree() {
		return service.getSystemMenuTree();
	}
	
	@ResponseBody
	@RequestMapping("/getMenu")
	public List<SystemMenu> getMenuByEmp() {
		Employee loginUser = UserContext.getUser();// 得到当前登录用户
		return service.getMenuByEmpId(loginUser.getId());
	}
}
