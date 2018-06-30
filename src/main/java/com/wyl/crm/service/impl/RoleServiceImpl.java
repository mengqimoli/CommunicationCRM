package com.wyl.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Role;
import com.wyl.crm.domain.RolePermissionLink;
import com.wyl.crm.mapper.RoleMapper;
import com.wyl.crm.query.RoleQuery;
import com.wyl.crm.service.IRoleService;
import com.wyl.crm.utils.PageResult;

@Service
public class RoleServiceImpl implements IRoleService {

	private RoleMapper dao;

	@Autowired
	public void setDao(RoleMapper dao) {
		// 由Spring注入dao
		this.dao = dao;

		// 自动建表
		dao.createTable();
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		// 保存角色，返回新id
		dao.save(role);
		// System.out.println(role);

		// 保存中间表
		saveRolePermissionLink(role);
	}

	// 保存中间表
	private void saveRolePermissionLink(Role role) {
		// 获取权限信息
		List<Permission> permissions = role.getPermissions();
		// 判断集合有效性
		if (!permissions.isEmpty()) {
			// 遍历权限
			Long role_id = role.getId();
			// 声明参数集合
			List<RolePermissionLink> links = new ArrayList<RolePermissionLink>();
			for (Permission permission : permissions) {
				// 建立角色和权限的关联
				RolePermissionLink link = new RolePermissionLink(role_id, permission.getId());
				// 把数据添加都集合中
				links.add(link);
			}
			// 保存关联
			dao.saveRolePermissionLinks(links);
		}
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		dao.update(role);
		// 删除关联
		dao.deleteRolePermissionLinks(role.getId());
		// 保存关联
		saveRolePermissionLink(role);
	}

	@Override
	public void delete(Long id) {
		// 删除关联
		dao.deleteRolePermissionLinks(id);
		
		dao.delete(id);
	}

	@Override
	public Role getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public List<Role> getAll() {
		return dao.getAll();
	}

	@Override
	public PageResult<Role> queryPage(RoleQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = dao.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Role> rows = dao.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<Role> getUserRoles(Long id) {
		return dao.getUserRoles(id);
	}

	

}
