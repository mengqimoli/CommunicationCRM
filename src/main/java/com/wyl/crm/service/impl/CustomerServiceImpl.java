package com.wyl.crm.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Pie;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.CustomerMapper;
import com.wyl.crm.query.CustomerQuery;
import com.wyl.crm.service.ICustomerService;
import com.wyl.crm.utils.PageResult;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerMapper mapper;

	@Override
	public void save(Customer obj) {
		mapper.save(obj);
	}

	@Override
	public void update(Customer obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public Customer getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<Customer> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<Customer> queryPage(CustomerQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Customer> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<SystemDictionaryItem> getCustomerSource() {
		return mapper.getCustomerSource();
	}

	@Override
	public List<Employee> getCustomerSeller() {
		// 查询营销人员
		return mapper.getCustomerSeller();
	}

	@Override
	public List<SystemDictionaryItem> getCustomerJob() {
		// 查询工作
		return mapper.getCustomerJob();
	}

	@Override
	public List<SystemDictionaryItem> getCustomerSalaryLevel() {
		// 查询收入
		return mapper.getCustomerSalaryLevel();
	}

	/**
	 * 导出客户表
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
	@Override
	public void getOutputFile(String[] heads, List<String[]> rows, ByteArrayOutputStream os, String FileName) {

		// 创建一个工作薄
		SXSSFWorkbook wb = new SXSSFWorkbook();
		// 创建表
		Sheet sheet = wb.createSheet();
		// 创建第一行
		Row herdRow = sheet.createRow(0);

		for (int i = 0; i < heads.length; i++) {
			Cell cell = herdRow.createCell(i);
			cell.setCellValue(heads[i]);
		}
		// 创建后面的行,根据list的长度确定创建的长度
		for (int i = 0; i < rows.size(); i++) {

			Row row = sheet.createRow(i + 1);
			// 跟据list里面string[]的长度确定没行的列数

			String[] datas = rows.get(i);
			for (int j = 0; j < datas.length; j++) {

				Cell createCell = row.createCell(j);
				// 设置值

				createCell.setCellValue(datas[j]);

			}

		}
		try {
			wb.write(os);
			wb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Customer getCustomerByName(String name) {
		return mapper.getCustomerByName(name);
	}

}
