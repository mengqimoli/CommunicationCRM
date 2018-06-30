package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Department;
import com.wyl.crm.query.DepartmentQuery;

public interface DepartmentMapper {
	// 自动建表
	void createTable();

	// 增
	void save(Department dept);

	// 删
	void delete(Long id);

	// 改
	void update(Department dept);

	// 查询一个
	Department getOne(Long id);

	// 查询所有
	List<Department> getAll();

	// 获取部门树
	List<Department> getDepartmentTree();

	// 分页集合查询
	List<Department> queryPage(DepartmentQuery qo);

	//获取分页查询总数
	Long queryCount(DepartmentQuery qo);

}
