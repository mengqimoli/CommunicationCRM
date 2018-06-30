package com.wyl.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.SystemLog;
import com.wyl.crm.query.SystemLogQuery;
import com.wyl.crm.service.ISystemLogService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller("systemLogController")
@RequestMapping("/systemLog")
@Resource("系统日志管理")
public class SystemLogController {
	@Autowired
	private ISystemLogService systemlogService;

	@Resource("系统日志页面导向")
	@RequestMapping("/index")
	public String index() {
		return "/systemLog/systemLog";
	}

	@Resource("系统日志列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemLogQuery qo) {
		PageResult<SystemLog> pr = null;
		pr = systemlogService.queryPage(qo);
		return pr;
	}

	@Resource("系统日志删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ar = null;
		try {
			systemlogService.delete(id);
			ar = new AjaxResult("日志删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ar = new AjaxResult("日志删除失败！",-101);
		}
		return ar;
	}
	
	@Resource("系统日志删除全部")
	@RequestMapping("/deleteALL")
	@ResponseBody
	public AjaxResult deleteALL(String ids) {
		String[] idArr = ids.split(",");
		List<Long> list = new ArrayList<>();
		for (String str : idArr) {
			list.add(Long.valueOf(str));
		}
		try {
			systemlogService.deleteAll(list);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("删除日志失败，原因是："+e.getMessage(),-101);
		}
	}
}
