package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.query.SystemMenuQuery;

public interface SystemMenuMapper {

	void createTable();

	void save(SystemMenu obj);

	void delete(Long id);

	void update(SystemMenu obj);

	SystemMenu getOne(Long id);

	// 查询所有
	List<SystemMenu> getAll();

	// 分页集合查询
	List<SystemMenu> queryPage(SystemMenuQuery query);

	// 获取分页查询总数
	Long queryCount(SystemMenuQuery query);

	// 根据用户id查询菜单列表
	List<SystemMenu> getSystemMenuByEmpId();

	// 获取系统菜单树
	List<SystemMenu> getSystemMenuTree();

	List<SystemMenu> getMenuByEmpId(Long id);

}
