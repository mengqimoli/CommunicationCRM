package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.query.RoleQuery;
import com.wyl.crm.utils.PageResult;

public interface IRoleService {

	void save(Role role);

	void update(Role role);

	void delete(Long id);

	Role getOne(Long id);

	List<Role> getAll();

	// 分页集合查询
	PageResult<Role> queryPage(RoleQuery query);

	// 查询用户对应的角色
	List<Role> getUserRoles(Long id);

}
