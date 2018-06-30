package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.EmployeeRoleLink;
import com.wyl.crm.query.EmployeeQuery;
import com.wyl.crm.utils.PageResult;

public interface IEmployeeService {

	void save(Employee emp);

	void delete(Long id);

	void update(Employee emp);

	Employee getOne(Long id);

	List<Employee> getAll();

	// 分页集合查询
	PageResult<Employee> queryPage(EmployeeQuery qo);

	// 用户登录
	Employee checkLogin(String username, String password);

	// 离职(改)
	void stopUse(Long id);

	// 复职(改)
	void startUse(Long id);

	// 根据username获取employee
	Employee getEmployeeByUsername(String username);


}