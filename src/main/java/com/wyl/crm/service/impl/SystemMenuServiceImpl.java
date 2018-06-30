package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.mapper.SystemMenuMapper;
import com.wyl.crm.query.SystemMenuQuery;
import com.wyl.crm.service.ISystemMenuService;
import com.wyl.crm.utils.PageResult;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

	private SystemMenuMapper mapper;

	@Autowired
	public void setMapper(SystemMenuMapper mapper) {
		// 由Spring注入mapper
		this.mapper = mapper;

		// 自动建表
		mapper.createTable();
	}

	@Override
	public void save(SystemMenu obj) {
		mapper.save(obj);
	}

	@Override
	public void update(SystemMenu obj) {
		System.out.println(".......//////////" + obj + ".......////////");
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public SystemMenu getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<SystemMenu> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<SystemMenu> queryPage(SystemMenuQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<SystemMenu> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	// 根据用户id查询菜单列表
	@Override
	public List<SystemMenu> getSystemMenuByEmpId(Long id) {
		return mapper.getSystemMenuByEmpId();
	}

	@Override
	public List<SystemMenu> getSystemMenuTree() {
		return mapper.getSystemMenuTree();
	}

	@Override
	public List<SystemMenu> getMenuByEmpId(Long id) {
		return mapper.getMenuByEmpId(id);
	}

}
