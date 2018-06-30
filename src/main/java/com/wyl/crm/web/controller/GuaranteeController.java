package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.query.GuaranteeQuery;
import com.wyl.crm.service.IGuaranteeItemService;
import com.wyl.crm.service.IGuaranteeService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/guarantee")
@Resource("保修单管理")
public class GuaranteeController {

	@Autowired
	private IGuaranteeService guaranteeService;
	@Autowired
	private IGuaranteeItemService guaranteeItemService;

	@RequestMapping("/index")
	@Resource("保修单页面导向")
	public String index() {
		return "guarantee/guarantee";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("保修单列表")
	public PageResult<Guarantee> list(GuaranteeQuery query) {
		return guaranteeService.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("保修单删除")
	public AjaxResult delete(Long id) {
		try {
			guaranteeService.delete(id);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("保修单保存/修改")
	public AjaxResult save(Guarantee guarantee) {
		try {
			if (guarantee != null && guarantee.getId() != null) {
				guaranteeService.update(guarantee);
			} else {
				guaranteeService.save(guarantee);
			}
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("操作失败" + e.getMessage());
		}
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public List<Guarantee> getAll() {
		List<Guarantee> guarantees = guaranteeService.getAll();
		return guarantees;
	}
}
