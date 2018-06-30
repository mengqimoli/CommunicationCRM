package com.wyl.crm.web.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.CustomerDevPlan;
import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.PotentialCustomerQuery;
import com.wyl.crm.service.ICustomerDevPlanService;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.service.IPotentialCustomerService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;
import com.wyl.crm.utils.UserContext;

@Controller
@RequestMapping("/potentialCustomer")
@Resource("潜在客户管理")
public class PotentialCustomerController {

	@Autowired
	private IPotentialCustomerService potentialCustomerServiceImpl;
	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private ICustomerDevPlanService customerDevPlanImpl;

	@Resource("潜在客户页面导向")
	@RequestMapping("/index")
	public String index() {
		return "potentialCustomer/potentialCustomer";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("潜在客户列表")
	public PageResult<PotentialCustomer> list(PotentialCustomerQuery query) {
		return potentialCustomerServiceImpl.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("潜在客户删除")
	public AjaxResult delete(Long id) {
		try {
			potentialCustomerServiceImpl.delete(id);
			return new AjaxResult("潜在客户删除成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("潜在客户删除失败！！！" + e.getMessage(), -101);
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("潜在客户保存/修改")
	public AjaxResult save(PotentialCustomer potentialCustomer) {
		// 声明
		AjaxResult ar = null;
		// 封装
		if (potentialCustomer.getId() == null) {
			// 录入时间为当前系统事件
			potentialCustomer.setInputUser(UserContext.getUser());
			potentialCustomer.setInputTime(new Date());
			potentialCustomerServiceImpl.save(potentialCustomer);
			ar = new AjaxResult("潜在客户保存成功！");
		} else {

			PotentialCustomer one = potentialCustomerServiceImpl.getOne(potentialCustomer.getId());
			potentialCustomer.setSeller(one.getSeller());
			potentialCustomer.setInputUser(one.getInputUser());
			potentialCustomerServiceImpl.update(potentialCustomer);
			ar = new AjaxResult("潜在客户修改成功！");
		}
		// 返回
		return ar;
	}

	// 潜在客户导入
	@RequestMapping("/imports")
	@Resource("潜在客户导入")
	@ResponseBody
	public AjaxResult imports(HttpServletRequest request, Model model) throws Exception {
		int adminId = 1;
		// 获取上传的文件
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("potentialCustomerExcle");
		System.out.println(file);
		String month = request.getParameter("month");
		InputStream in = file.getInputStream();
		// 数据导入
		potentialCustomerServiceImpl.importExcelInfo(in, file, month, adminId);
		in.close();
		return new AjaxResult("潜在客户导入成功！！！");
	}

	/*@RequestMapping("/developCustomer")
	@ResponseBody
	@Resource("潜在客户开发")
	public AjaxResult developCustomer(CustomerDevPlan customerDevPlan) {
		try {
			PotentialCustomer customer = potentialCustomerServiceImpl.getOne(customerDevPlan.getPote().getId());
			customer.setSeller(customerDevPlan.getSeller());
			potentialCustomerServiceImpl.update(customer);
			customerDevPlanImpl.save(customerDevPlan);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage(), -101);
		}
	}*/

	@RequestMapping("/designateCustomer")
	@ResponseBody
	@Resource("潜在客户指派")
	public AjaxResult designateCustomer(PotentialCustomer potentialCustomer) {
		try {
			if (potentialCustomer.getId() != null) {
				PotentialCustomer customer = potentialCustomerServiceImpl.getOne(potentialCustomer.getId());
				customer.setSeller(potentialCustomer.getSeller());
				potentialCustomerServiceImpl.update(customer);
			}
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage(), -101);
		}
	}

	@RequestMapping("/changeToCustomer")
	@ResponseBody
	@Resource("潜在客户转正式客户")
	public AjaxResult changeToCustomer(Customer customer) {
		try {
			// 删除潜在客户开发计划
			customerDevPlanImpl.deleteByPote(customer.getName());
			// 删除潜在客户
			potentialCustomerServiceImpl.deleteByName(customer.getName());
			// 保存正式客户
			customer.setInputUser(UserContext.getUser());
			customer.setInputTime(new Date());
			customer.setCustomerStatus(new SystemDictionaryItem(22L));
			customer.setPool(-1);
			customerServiceImpl.save(customer);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage(), -101);
		}
	}

	@RequestMapping("/customerSource")
	@ResponseBody
	public List<SystemDictionaryItem> customerSource() {
		return potentialCustomerServiceImpl.getCustomerSource();
	}

}
