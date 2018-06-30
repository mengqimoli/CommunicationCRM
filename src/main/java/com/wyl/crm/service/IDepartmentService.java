package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Department;
import com.wyl.crm.query.DepartmentQuery;
import com.wyl.crm.utils.PageResult;

public interface IDepartmentService {
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

	// 分页集合查询
	PageResult<Department> queryPage(DepartmentQuery qo);

	// 获取部门树
	List<Department> getDepartmentTree();

	// 停用部门(改)
	void stopUse(Long id);

	// 启用部门(改)
	void startUse(Long id);

}
