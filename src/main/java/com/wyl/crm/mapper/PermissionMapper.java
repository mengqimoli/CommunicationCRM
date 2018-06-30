package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Permission;
import com.wyl.crm.query.PermissionQuery;

public interface PermissionMapper {

	void createTable();

	void save(Permission obj);

	void delete(Long id);

	void update(Permission obj);

	Permission getOne(Long id);

	List<Permission> getAll();

	List<Permission> queryPage(PermissionQuery query);

	Long queryCount(PermissionQuery query);

	// 获取角色权限
	List<Permission> getRolePermissions(Long id);

	// 获取用户权限
	List<Permission> getUserPermissions(Long id);

	// 根据资源获取权限
	Permission getPermissionByResourceName(String resource);

	// 根据名字获取权限
	Permission getPermissionByPermissionName(String permissionName);

}
