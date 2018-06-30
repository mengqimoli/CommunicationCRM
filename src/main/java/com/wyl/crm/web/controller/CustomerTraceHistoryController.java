package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.CustomerTraceHistory;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerTraceHistoryQuery;
import com.wyl.crm.service.ICustomerTraceHistoryService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/customerTraceHistory")
@Resource("客户跟进历史管理")
public class CustomerTraceHistoryController {

	@Autowired
	private ICustomerTraceHistoryService historyServiceImpl;

	@Resource("客户跟进历史页面导向")
	@RequestMapping("/index")
	public String index() {
		return "customerTraceHistory/customerTraceHistory";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("客户跟进历史列表")
	public PageResult<CustomerTraceHistory> list(CustomerTraceHistoryQuery query) {
		return historyServiceImpl.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("客户跟进历史删除")
	public AjaxResult delete(Long id) {

		try {
			historyServiceImpl.delete(id);
			return new AjaxResult("客户跟进历史删除成功!!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("客户跟进历史删除失败!!!", -101);
		}

	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("客户跟进历史保存/修改")
	public AjaxResult save(CustomerTraceHistory customerTraceHistory) {

		if (customerTraceHistory != null && customerTraceHistory.getId() != null) {
			try {
				historyServiceImpl.update(customerTraceHistory);
				return new AjaxResult("客户跟进历史修改成功!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("客户跟进历史修改失败!!!", -101);
			}

		} else {

			try {
				// 录入时间为当前系统事件
				historyServiceImpl.save(customerTraceHistory);
				return new AjaxResult("客户跟进历史保存成功!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("客户跟进历史保存失败!!!", -101);
			}
		}

	}

	@RequestMapping("/customerSource")
	@ResponseBody
	public List<SystemDictionaryItem> customerSource() {
		return historyServiceImpl.getCustomerSource();
	}

	// 跟进人员(营销人员)
	@RequestMapping("/seller")
	@ResponseBody
	public List<Employee> seller() {
		return historyServiceImpl.getCustomerSeller();
	}

}
