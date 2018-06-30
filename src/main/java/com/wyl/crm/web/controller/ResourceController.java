package com.wyl.crm.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Resource;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.ResourceQuery;
import com.wyl.crm.service.IResourceService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	private boolean initialize = false;

	@Autowired
	private IResourceService service;

	@RequestMapping("/index")
	public String index() {
		return "resource/resource";
	}

	@RequestMapping("/list")
	@ResponseBody()
	public PageResult<Resource> list(ResourceQuery query) {
		System.out.println(query);
		return service.queryPage(query);
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Resource resource) {
		// 准备返回对象
		AjaxResult ar;
		try {
			if (resource.getId() == null) {
				service.save(resource);
				ar = new AjaxResult("资源保存成功！！");
			} else {
				service.update(resource);
				ar = new AjaxResult("资源修改成功！！");
			}

		} catch (LogicException e) {
			ar = new AjaxResult("资源保存失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar;
		try {
			service.delete(id);
			ar = new AjaxResult("资源删除成功！！");
		} catch (LogicException e) {
			ar = new AjaxResult("资源删除失败:" + e.getMessage(), e.getErrorCode());
		}

		return ar;
	}

	@RequestMapping("/importMoudleResource")
	@ResponseBody()
	public AjaxResult importMoudleResource(String controller) {
		if (controller != null) {
			service.importControllerResources(controller);
		}

		return new AjaxResult();
	}

	@RequestMapping("/getControllerTree")
	@ResponseBody()
	public List<Map> getControllerTree(String id) {
		if (!initialize) {
			service.initControllers();
			initialize = true;
		}

		List<Map> list = new ArrayList<>();
		// 包展示
		Map<String, List<String>> controllers = service.getControllers();
		if (id == null) {
			for (String p : controllers.keySet()) {
				Map<String, Object> pkg = new HashMap<>();
				pkg.put("id", p);
				pkg.put("text", p);
				pkg.put("state", "closed");
				pkg.put("attributes", true);
				list.add(pkg);
			}
		} else {// 模块展示
			List<String> ms = controllers.get(id);
			for (String controller : ms) {
				Map<String, Object> controllerMap = new HashMap<>();
				controllerMap.put("id", controller);
				controllerMap.put("text", controller);
				list.add(controllerMap);
			}
		}
		return list;
	}

	// 加入权限
	@RequestMapping("/importPermission")
	@ResponseBody
	public AjaxResult importPermission(String controller) {
		if (controller != null) {
			try {
				service.importPermission(controller);
				return new AjaxResult("权限加入成功，请于权限管理中查看！！！");
			} catch (Exception e) {
				return new AjaxResult("权限加入失败", 101);
			}
		}
		return new AjaxResult("请先选中左侧一个控制器(controller)！！！", 101);
	}

}
