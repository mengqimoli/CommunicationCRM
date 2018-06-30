package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Permission;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.mapper.PermissionMapper;
import com.wyl.crm.query.PermissionQuery;
import com.wyl.crm.service.IPermissionService;
import com.wyl.crm.utils.PageResult;

@Service
public class PermissionServiceImpl implements IPermissionService {

	private PermissionMapper dao;

	@Autowired
	public void setDao(PermissionMapper dao) {
		// 由Spring注入dao
		this.dao = dao;

		// 自动建表
		dao.createTable();
	}

	@Override
	public void save(Permission permission) {
		dao.save(permission);
	}

	@Override
	public void update(Permission permission) {
		dao.update(permission);
	}

	@Override
	public void delete(Long id) {
		Permission permission = dao.getOne(id);
		if (permission == null) {
			throw new LogicException("没有该数据", -102);
		}
		dao.delete(id);
	}

	@Override
	public Permission getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public List<Permission> getAll() {
		return dao.getAll();
	}

	@Override
	public PageResult<Permission> queryPage(PermissionQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = dao.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Permission> rows = dao.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<Permission> getRolePermissions(Long id) {
		return dao.getRolePermissions(id);
	}

	@Override
	public List<Permission> getUserPermissions(Employee emp) {
		return dao.getUserPermissions(emp.getId());
	}

	@Override
	public Permission getPermissionByResourceName(String resource) {
		return dao.getPermissionByResourceName(resource);
	}

	@Override
	public Permission getPermissionByPermissionName(String permissionName) {
		return dao.getPermissionByPermissionName(permissionName);
	}

}
