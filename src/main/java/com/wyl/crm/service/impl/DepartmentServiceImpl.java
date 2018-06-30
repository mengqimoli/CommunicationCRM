package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Department;
import com.wyl.crm.mapper.DepartmentMapper;
import com.wyl.crm.query.DepartmentQuery;
import com.wyl.crm.service.IDepartmentService;
import com.wyl.crm.utils.PageResult;

/**
 * 部门服务
 * 
 * @author admin
 *
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

	private DepartmentMapper mapper;

	/**
	 * Autowired: 根据类型注入（字段值 && ） 可以标记在字段上，代表类在初始化时，会自动把字段需要的实例赋值过来
	 * 
	 * 可以标记在方法和构造函数上，代表类在初始化时，会自动把方法需要的类型参数，从Spring容器中取出，并传入
	 * 
	 * Resource： 根据名字注入
	 */
	@Autowired
	public void setMapper(DepartmentMapper mapper) {
		this.mapper = mapper;

		// 自动建表
		this.mapper.createTable();
	}

	@Override
	public void save(Department dept) {
		mapper.save(dept);

	}

	@Override
	public void update(Department dept) {
		mapper.update(dept);

	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);

	}

	@Override
	public Department getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<Department> getAll() {
		return mapper.getAll();
	}

	@Override
	public List<Department> getDepartmentTree() {
		return mapper.getDepartmentTree();
	}

	@Override
	public void stopUse(Long id) {
		Department dept = mapper.getOne(id);
		if (dept != null) {
			dept.setStatus(-1);
			mapper.update(dept);
		}
	}

	@Override
	public void startUse(Long id) {
		Department dept = mapper.getOne(id);
		if (dept != null) {
			dept.setStatus(1);
			mapper.update(dept);
		}
	}

	@Override
	public PageResult<Department> queryPage(DepartmentQuery qo) {
		// 声明
		PageResult<Department> pr = new PageResult<Department>();
		// 封装
		// 当前页数据
		List<Department> rows = mapper.queryPage(qo);
		pr.setRows(rows);

		// 符合条件总记录数
		Long total = mapper.queryCount(qo);
		pr.setTotal(total);
		// 返回
		return pr;
	}

}
