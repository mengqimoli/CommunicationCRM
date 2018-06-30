package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.domain.RolePermissionLink;
import com.wyl.crm.query.RoleQuery;

public interface RoleMapper {

	void createTable();

	void save(Role obj);

	void update(Role obj);

	void delete(Long id);

	Role getOne(Long id);

	List<Role> getAll();

	List<Role> queryPage(RoleQuery query);

	Long queryCount(RoleQuery query);

	// 批量添加关联信息
	void saveRolePermissionLinks(List<RolePermissionLink> links);

	// 清除关联关系
	void deleteRolePermissionLinks(Long id);

	// 根据用户查询角色
	List<Role> getUserRoles(Long id);

}
