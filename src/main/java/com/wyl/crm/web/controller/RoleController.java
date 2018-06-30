package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.RoleQuery;
import com.wyl.crm.service.IRoleService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/role")
@Resource("角色管理")
public class RoleController {

	@Autowired
	private IRoleService service;

	@Resource("角色页面导向")
	@RequestMapping("/index")
	public String index() {
		return "role/role";
	}

	@Resource("角色列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(RoleQuery query) {
		PageResult<Role> pr = service.queryPage(query);
		return pr;
	}

	@Resource("角色保存/修改")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Role role) {
		// 准备返回对象
		AjaxResult ar;
		try {
			if (role.getId() == null) {
				// 调用业务逻辑
				service.save(role);
				// 封装返回结果
				ar = new AjaxResult("角色保存成功！！");
			} else {
				// 调用业务逻辑
				service.update(role);
				// 封装返回结果
				ar = new AjaxResult("角色修改成功！！");
			}

		} catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("角色保存失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@Resource("角色删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar;
		try {
			service.delete(id);
			ar = new AjaxResult("角色删除成功！！");
		} catch (LogicException e) {
			ar = new AjaxResult("角色删除失败:" + e.getMessage(), e.getErrorCode());
		}

		return ar;
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public List<Role> getAll() {
		List<Role> roles = service.getAll();
		return roles;
	}

	@RequestMapping("/getUserRoles")
	@ResponseBody
	public List<Role> getUserRoles(Long id) {
		return service.getUserRoles(id);
	}
}
