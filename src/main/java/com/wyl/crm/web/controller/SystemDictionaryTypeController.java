package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Role;
import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.SystemDictionaryTypeQuery;
import com.wyl.crm.service.ISystemDictionaryTypeService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/systemDictionaryType")
@Resource("数据字典管理")
public class SystemDictionaryTypeController {

	@Autowired
	private ISystemDictionaryTypeService service;

	@Resource("数据字典页面导向")
	@RequestMapping("/index")
	public String index() {
		return "systemDictionary/systemDictionary";
	}

	@Resource("数据字典列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemDictionaryTypeQuery query) {
		PageResult<SystemDictionaryType> pr = service.queryPage(query);
		return pr;
	}

	@Resource("数据字典保存/修改")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemDictionaryType systemDictionaryType) {
		// 准备返回对象
		AjaxResult ar;
		try {
			if (systemDictionaryType.getId() == null) {
				// 调用业务逻辑
				service.save(systemDictionaryType);
				// 封装返回结果
				ar = new AjaxResult("数据字典保存成功！！！");
			} else {
				// 调用业务逻辑
				service.update(systemDictionaryType);
				// 封装返回结果
				ar = new AjaxResult("数据字典修改成功！！！");
			}

		} catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("数据字典编辑失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@Resource("数据字典删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar;
		try {
			service.delete(id);
			ar = new AjaxResult("数据字典删除成功！！！");
		} catch (LogicException e) {
			ar = new AjaxResult("数据字典删除失败:" + e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<SystemDictionaryType> getAll() {
		List<SystemDictionaryType> systemDictionaryTypes = service.getAll();
		return systemDictionaryTypes;
	}
}
