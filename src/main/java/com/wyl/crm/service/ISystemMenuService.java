package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.query.SystemMenuQuery;
import com.wyl.crm.utils.PageResult;

public interface ISystemMenuService {

	void save(SystemMenu obj);

	void delete(Long id);

	void update(SystemMenu obj);

	SystemMenu getOne(Long id);

	//查询所有
	List<SystemMenu> getAll();

	// 分页集合查询
	PageResult<SystemMenu> queryPage(SystemMenuQuery query);

	//根据用户id查询菜单列表
	List<SystemMenu> getSystemMenuByEmpId(Long id);

	//获取菜单树
	List<SystemMenu> getSystemMenuTree();

	List<SystemMenu> getMenuByEmpId(Long id);

}
