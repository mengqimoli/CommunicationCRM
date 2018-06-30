package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.query.PermissionQuery;
import com.wyl.crm.utils.PageResult;

public interface IPermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);

	Permission getOne(Long id);

	List<Permission> getAll();

	// 分页集合查询
	PageResult<Permission> queryPage(PermissionQuery query);

	// 获取角色权限
	List<Permission> getRolePermissions(Long id);

	// 获取用户权限
	List<Permission> getUserPermissions(Employee emp);

	// 根据资源获取权限
	Permission getPermissionByResourceName(String resource);

	// 根据名字获取权限
	Permission getPermissionByPermissionName(String permissionName);

}
