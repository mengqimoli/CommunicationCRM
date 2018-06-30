package com.wyl.crm.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.CustomerTransfer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Pie;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerQuery;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.service.ICustomerTransferService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.Resource;
import com.wyl.crm.utils.UserContext;

@Controller
@RequestMapping("/customer")
@Resource("客户信息管理")
public class CustomerController {

	@Autowired
	private ICustomerService customerServiceImpl;
	@Autowired
	private ICustomerTransferService customerTransferServiceImpl;

	@Resource("客户信息页面导向")
	@RequestMapping("/index")
	public String index() {
		return "customer/customer";
	}

	@RequestMapping("/list")
	@ResponseBody
	@Resource("客户信息列表")
	public PageResult<Customer> list(CustomerQuery query) {
		query.setPool(-1);
		return customerServiceImpl.queryPage(query);
	}

	@RequestMapping("/delete")
	@ResponseBody
	@Resource("客户信息删除")
	public AjaxResult delete(Long id) {
		try {
			customerServiceImpl.delete(id);
			return new AjaxResult("正式客户删除成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult("正式客户删除失败！！！" + e.getMessage(), -101);
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	@Resource("客户信息保存/修改")
	public AjaxResult save(Customer customer) {
		if (customer != null && customer.getId() != null) {
			try {
				Customer one = customerServiceImpl.getOne(customer.getId());
				customer.setCustomerStatus(one.getCustomerStatus());
				customerServiceImpl.update(customer);
				return new AjaxResult("正式客户修改成功！！！");
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxResult("正式客户修改成功！！！" + e.getMessage(), -101);
			}
		} else {
			try {
				System.out.println(customer.getStatus() + "...........////////");
				// 录入时间为当前系统事件
				customer.setInputUser(UserContext.getUser());
				customer.setInputTime(new Date());
				customer.setCustomerStatus(new SystemDictionaryItem(22L));
				customer.setPool(-1);
				customerServiceImpl.save(customer);
				return new AjaxResult("正式客户保存成功！！！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new AjaxResult("正式客户保存失败！！！" + e.getMessage());
			}
		}
	}

	@RequestMapping("/putPool")
	@ResponseBody
	@Resource("客户放入资源池")
	public AjaxResult putPool(Long id) {
		System.out.println("ssssssssss" + id);
		try {
			Customer customer = customerServiceImpl.getOne(id);
			customer.setPool(1);
			customerServiceImpl.update(customer);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage(), -101);
		}
	}

	@RequestMapping("/poolList")
	@ResponseBody
	@Resource("客户资源池列表")
	public PageResult<Customer> poolList(CustomerQuery query) {
		query.setPool(1);
		return customerServiceImpl.queryPage(query);
	}

	@Resource("客户资源池页面导向")
	@RequestMapping("/customerResourcePool")
	public String customerResourcePool() {
		return "customerResourcePool/customerResourcePool";
	}

	@RequestMapping("/followCustomer")
	@ResponseBody
	@Resource("客户跟进")
	public AjaxResult followCustomer(Long id) {
		try {
			Customer customer = customerServiceImpl.getOne(id);
			customer.setSeller(UserContext.getUser());
			customer.setPool(-1);
			customerServiceImpl.update(customer);
			return new AjaxResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage(), -101);
		}
	}

	@RequestMapping("/transferCustomer")
	@ResponseBody
	@Resource("客户移交")
	public AjaxResult transferCustomer(CustomerTransfer customerTransfer, Long id) {
		try {
			Customer one = customerServiceImpl.getOne(id);
			// 保存客户移交记录
			customerTransfer.setCustomer(one);
			customerTransfer.setTransUser(UserContext.getUser());
			customerTransfer.setOldSeller(one.getSeller());
			customerTransfer.setTransTime(new Date());
			customerTransferServiceImpl.save(customerTransfer);
			// 修改客户的营销人员
			one.setSeller(customerTransfer.getNewSeller());
			customerServiceImpl.update(one);
			return new AjaxResult();
		} catch (Exception e) {
			return new AjaxResult(e.getMessage(),-101);
		}
	}

	/**
	 * 客户导出
	 * 
	 * @param response
	 * @param query
	 * @throws IOException
	 */
	@RequestMapping("/exports")
	@Resource("客户信息导出")
	public void export(HttpServletResponse response, CustomerQuery query) throws IOException {
		String title = "客户";
		PageResult<Customer> pageList = customerServiceImpl.queryPage(query);
		List<Customer> data = pageList.getRows();
		// 准备第一行的数据
		String[] heads = { "客户姓名", "客户性别", "客户电话", "客户Email", "客户QQ", "客户微信", "客户职业", "客户收入阶梯", "客户来源" };

		List<String[]> rows = new ArrayList<>();
		for (Customer customer : data) {
			String[] strings = new String[heads.length];

			// strings[0] = customer.getId() == null ? "" :
			// customer.getId().toString();
			strings[0] = customer.getName();
			if (customer.isSex()) {
				strings[1] = "男";
			} else {
				strings[1] = "女";
			}
			strings[2] = customer.getTel();
			strings[3] = customer.getEmail();
			strings[4] = customer.getQq();
			strings[5] = customer.getWechat();
			strings[6] = customer.getJob().getName();
			strings[7] = customer.getSalaryLevel().getName();
			strings[8] = customer.getCustomerSource().getName();

			rows.add(strings);
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		customerServiceImpl.getOutputFile(heads, rows, os, title + ".xlsx");
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(("customerInfo.xlsx").getBytes(), "iso-8859-1"));
		response.setHeader("Location", "E:/html");
		response.setContentLength(content.length);
		ServletOutputStream outputStream = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(outputStream);
		byte[] buff = new byte[81929];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);

		}
		bis.close();
		bos.close();
		outputStream.flush();
		outputStream.close();
	}

	@RequestMapping("/getOne")
	@ResponseBody
	public Customer getOne(Long id) {
		return customerServiceImpl.getOne(id);
	}

	@RequestMapping("/customerSource")
	@ResponseBody
	public List<SystemDictionaryItem> getSource() {
		return customerServiceImpl.getCustomerSource();
	}

	@RequestMapping("/customerSeller")
	@ResponseBody
	public List<Employee> getSeller() {
		return customerServiceImpl.getCustomerSeller();
	}

	@RequestMapping("/customerJob")
	@ResponseBody
	public List<SystemDictionaryItem> getJob() {
		return customerServiceImpl.getCustomerJob();
	}

	@RequestMapping("/customerSalaryLevel")
	@ResponseBody
	public List<SystemDictionaryItem> getSalaryLevel() {
		return customerServiceImpl.getCustomerSalaryLevel();
	}

}
