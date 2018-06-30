package com.wyl.crm.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Pie;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerQuery;
import com.wyl.crm.utils.PageResult;

public interface ICustomerService {

	void save(Customer obj);

	void delete(Long id);

	void update(Customer obj);

	Customer getOne(Long id);

	// 查询所有
	List<Customer> getAll();

	// 分页集合查询
	PageResult<Customer> queryPage(CustomerQuery query);

	// 获取客户来源
	List<SystemDictionaryItem> getCustomerSource();

	// 获取客户营销人员
	List<Employee> getCustomerSeller();

	// 获取客户职业
	List<SystemDictionaryItem> getCustomerJob();

	// 获取客户收入阶梯
	List<SystemDictionaryItem> getCustomerSalaryLevel();
	
	/**
	 * 
	 * @param title
	 *            标题
	 * @param column
	 *            每行数据的属性名
	 * @param data
	 *            数据
	 * @param fileName
	 *            文件名
	 */
	// 导出员工表
	void getOutputFile(String[] heads, List<String[]> rows, ByteArrayOutputStream os, String FileName);

	Customer getCustomerByName(String name);

}
