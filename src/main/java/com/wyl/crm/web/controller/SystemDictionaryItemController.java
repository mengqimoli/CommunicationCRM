package com.wyl.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.SystemDictionaryItemQuery;
import com.wyl.crm.service.ISystemDictionaryItemService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/systemDictionaryItem")
@Resource("数据字典明细管理")
public class SystemDictionaryItemController {

	@Autowired
	private ISystemDictionaryItemService service;
	
	@Resource("数据字典明细列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(SystemDictionaryItemQuery query){
		PageResult<SystemDictionaryItem> pr = service.queryPage(query);
		return pr;
	} 
	
	@Resource("数据字典明细保存/修改")
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemDictionaryItem systemDictionaryItem){
		// 准备返回对象
		AjaxResult ar;
		try{
			if(systemDictionaryItem.getId()==null){
				// 调用业务逻辑
				service.save(systemDictionaryItem);
				// 封装返回结果
				ar = new AjaxResult("数据字典明细明细保存成功！！");
			}else{
				// 调用业务逻辑
				service.update(systemDictionaryItem);
				// 封装返回结果
				ar = new AjaxResult("数据字典明细明细修改成功！！");
			}
			
		}catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("数据字典明细编辑失败:" + e.getMessage(),e.getErrorCode());
		}
		// 返回
		return ar;
	} 
	
	@Resource("数据字典明细删除")
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult ar ;
		try{
			service.delete(id);
			ar= new AjaxResult("数据字典明细删除成功！！");
		}catch (LogicException e) {
			ar= new AjaxResult("数据字典明细删除失败:" + e.getMessage(),e.getErrorCode());
		}
		return ar;
	} 
}
