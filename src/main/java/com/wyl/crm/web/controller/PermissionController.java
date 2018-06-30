package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Permission;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.PermissionQuery;
import com.wyl.crm.service.IPermissionService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/permission")
@Resource("权限管理")
public class PermissionController {

	@Autowired
	private IPermissionService service;

	@Resource("权限页面导向")
	@RequestMapping("/index")
	public String index() {
		return "permission/permission";
	}

	@Resource("权限列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult<Permission> list(PermissionQuery query) {
		System.out.println(query.getPermissionModelType()+".........////////");
		PageResult<Permission> pr = service.queryPage(query);
		return pr;
	}

	@Resource("权限保存/修改")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Permission permission) {
		System.out.println(permission);
		// 准备返回对象
		AjaxResult ar;
		try {
			if (permission.getId() == null) {
				service.save(permission);
				ar = new AjaxResult("权限保存成功！！");
			} else {
				service.update(permission);
				ar = new AjaxResult("权限修改成功！！");
			}

		} catch (LogicException e) {
			ar = new AjaxResult("权限保存失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@Resource("权限删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar;
		try {
			service.delete(id);
			ar = new AjaxResult("权限删除成功！！");
		} catch (LogicException e) {
			ar = new AjaxResult("权限删除失败:" + e.getMessage(), e.getErrorCode());
		}

		return ar;
	}

	@RequestMapping("/getRolePermissions")
	@ResponseBody
	public List<Permission> getRolePermissions(Long id) {
		return service.getRolePermissions(id);
	}
	
	@RequestMapping("/getPermissionManagerTree")
	@ResponseBody
	public List<Permission> getPermissionManagerTree(Long id) {
		return service.getRolePermissions(id);
	}
}
