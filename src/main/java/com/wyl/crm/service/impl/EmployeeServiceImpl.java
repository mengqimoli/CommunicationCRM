package com.wyl.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.EmployeeRoleLink;
import com.wyl.crm.domain.Role;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.mapper.EmployeeMapper;
import com.wyl.crm.query.EmployeeQuery;
import com.wyl.crm.service.IEmployeeService;
import com.wyl.crm.utils.PageResult;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeMapper mapper;

	/**
	 * Autowired: 根据类型注入（字段值 && ） 可以标记在字段上，代表类在初始化时，会自动把字段需要的实例赋值过来
	 * 
	 * 可以标记在方法和构造函数上，代表类在初始化时，会自动把方法需要的类型参数，从Spring容器中取出，并传入
	 * 
	 * Resource： 根据名字注入
	 */
	@Autowired
	public void setMapper(EmployeeMapper mapper) {
		this.mapper = mapper;

		// 自动建表
		this.mapper.createTable();
	}

	@Override
	public void save(Employee emp) {
		mapper.save(emp);

		// 保存中间表
		saveEmployeeRoleLink(emp);
	}

	private void saveEmployeeRoleLink(Employee emp) {
		// 获取集合
		List<Role> roles = emp.getRoles();

		if (roles != null && !roles.isEmpty()) {
			Long empId = emp.getId();
			List<EmployeeRoleLink> list = new ArrayList<>();
			for (Role role : roles) {
				EmployeeRoleLink link = new EmployeeRoleLink();
				link.setEmployee_id(empId);
				link.setRole_id(role.getId());
				list.add(link);
			}
			mapper.saveEmployeeRoleLinks(list);
		}
	}

	@Override
	public void delete(Long id) {
		mapper.deleteEmployeeRoleLinks(id);
		mapper.delete(id);
	}

	@Override
	public void update(Employee emp) {
		// TODO Auto-generated method stub
		mapper.update(emp);
		
		// 清除中间表
		mapper.deleteEmployeeRoleLinks(emp.getId());
		// 保存中间表
		saveEmployeeRoleLink(emp);
	}

	@Override
	public Employee getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<Employee> getAll() {
		return mapper.getAll();
	}

	// 分页集合查询
	@Override
	public PageResult<Employee> queryPage(EmployeeQuery qo) {
		// 声明
		PageResult<Employee> pr = new PageResult<Employee>();
		// 封装
		// 当前页数据
		List<Employee> rows = mapper.queryPage(qo);
		pr.setRows(rows);

		// 符合条件总记录数
		Long total = mapper.queryCount(qo);
		pr.setTotal(total);
		// 返回
		return pr;
	}

	@Override
	public Employee checkLogin(String username, String password) {
		// 检查用户名
		List<Employee> list = mapper.getEmployeeByUsername(username);
		if (list == null || list.size() == 0 || list.get(0) == null) {
			throw new LogicException("用户名错误！", -100);
		}
		Employee employee = list.get(0);
		// 检查密码
		if (!employee.getPassword().equals(password)) {
			throw new LogicException("密码错误！", -101);
		}
		return employee;
	}

	@Override
	public void stopUse(Long id) {
		Employee employee = mapper.getOne(id);
		if (employee != null) {
			employee.setStatus(-1);
			mapper.update(employee);
		}

	}

	@Override
	public void startUse(Long id) {
		Employee employee = mapper.getOne(id);
		if (employee != null) {
			employee.setStatus(1);
			mapper.update(employee);
		}
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		List<Employee> list = mapper.getEmployeeByUsername(username);
		if (list.size() > 0) {// 必须判断size
			return list.get(0);
		}
		return null;
	}

}
