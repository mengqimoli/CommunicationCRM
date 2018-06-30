package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.query.ContractQuery;
import com.wyl.crm.service.IContractItemService;
import com.wyl.crm.service.IContractService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/contract")
@Resource("合同管理")
public class ContractController {

	@Autowired
	private IContractService contractService;
	@Autowired
	private IContractItemService contractItemService;

	@RequestMapping("/index")
	@Resource("合同页面导向")
	public String index() {
		return "contract/contract";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("合同列表")
	public PageResult<Contract> list(ContractQuery query) {
		return contractService.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("合同删除")
	public AjaxResult delete(Long id) {
		try {
			contractItemService.deleteByContractId(id);
			contractService.delete(id);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("合同保存/修改")
	public AjaxResult save(Contract contract) {
		try {
			if (contract != null && contract.getId() != null) {
				contractService.update(contract);
			} else {
				contractService.save(contract);
			}
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("操作失败" + e.getMessage());
		}
	}

	@RequestMapping("/getAll")
	@ResponseBody
	public List<Contract> getAll() {
		List<Contract> contracts = contractService.getAll();
		return contracts;
	}
}
