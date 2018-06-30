package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.EmployeeRoleLink;
import com.wyl.crm.query.EmployeeQuery;

public interface EmployeeMapper {

	void createTable();

	void save(Employee obj);

	void delete(Long id);

	void update(Employee obj);

	Employee getOne(Long id);

	// 查询所有
	List<Employee> getAll();

	// 分页集合查询
	List<Employee> queryPage(EmployeeQuery qo);

	// 获取分页查询总数
	Long queryCount(EmployeeQuery qo);

	// 根据username获取employee
	List<Employee> getEmployeeByUsername(String username);

	// 保存中间表
	void saveEmployeeRoleLinks(List<EmployeeRoleLink> list);

	// 删除关联
	void deleteEmployeeRoleLinks(Long id);
}
