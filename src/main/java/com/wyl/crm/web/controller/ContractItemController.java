package com.wyl.crm.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xpath.internal.operations.And;
import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.query.ContractItemQuery;
import com.wyl.crm.service.IContractItemService;
import com.wyl.crm.service.IContractService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/contractItem")
@Resource("合同明细管理")
public class ContractItemController {

	@Autowired
	private IContractItemService contractItemService;

	@RequestMapping("/list")
	@ResponseBody
	@Resource("合同明细列表")
	public PageResult<ContractItem> list(ContractItemQuery query) {
		return contractItemService.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("合同明细删除")
	public AjaxResult delete(Long id) {
		try {
			contractItemService.delete(id);
			return new AjaxResult("该合同明细删除成功！！！");
		} catch (LogicException e) {
			return new AjaxResult(e.getMessage(), e.getErrorCode());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Resource("合同明细保存/修改")
	public AjaxResult save(ContractItem contractItem) {
		if (contractItem != null && contractItem.getId() != null) {
			try {
				contractItemService.update(contractItem);
				return new AjaxResult("该合同明细修改成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该合同明细修改失败！！！", -101);
			}
		} else {
			try {
				contractItemService.save(contractItem);
				return new AjaxResult("该合同明细保存成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("该合同明细保存失败！！！", -101);
			}
		}
	}

	@RequestMapping("/getItem")
	@ResponseBody
	public List<ContractItem> getItem(Long id) {
		return contractItemService.getItem(id);
	}
}
