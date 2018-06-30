package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.mapper.SystemDictionaryTypeMapper;
import com.wyl.crm.query.SystemDictionaryTypeQuery;
import com.wyl.crm.service.ISystemDictionaryTypeService;
import com.wyl.crm.utils.PageResult;

@Service
public class SystemDictionaryTypeServiceImpl implements ISystemDictionaryTypeService {

	private SystemDictionaryTypeMapper dao;

	@Autowired
	public void setDao(SystemDictionaryTypeMapper dao) {
		// 由Spring注入dao
		this.dao = dao;

		// 自动建表
		dao.createTable();
	}

	@Override
	public void save(SystemDictionaryType obj) {
		dao.save(obj);
	}

	@Override
	public void update(SystemDictionaryType obj) {
		dao.update(obj);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public SystemDictionaryType getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public List<SystemDictionaryType> getAll() {
		return dao.getAll();
	}

	public PageResult<SystemDictionaryType> queryPage(SystemDictionaryTypeQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = dao.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<SystemDictionaryType> rows = dao.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

}
