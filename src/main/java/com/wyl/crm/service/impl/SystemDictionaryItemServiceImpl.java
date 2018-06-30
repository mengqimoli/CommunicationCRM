package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.mapper.SystemDictionaryItemMapper;
import com.wyl.crm.query.SystemDictionaryItemQuery;
import com.wyl.crm.service.ISystemDictionaryItemService;
import com.wyl.crm.utils.PageResult;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {

	private SystemDictionaryItemMapper dao;

	@Autowired
	public void setDao(SystemDictionaryItemMapper dao) {
		// 由Spring注入dao
		this.dao = dao;

		// 自动建表
		dao.createTable();
	}

	@Override
	public void save(SystemDictionaryItem obj) {
		dao.save(obj);
	}

	@Override
	public void update(SystemDictionaryItem obj) {
		dao.update(obj);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public SystemDictionaryItem getOne(Long id) {
		return dao.getOne(id);
	}

	@Override
	public List<SystemDictionaryItem> getAll() {
		return dao.getAll();
	}

	@Override
	public PageResult<SystemDictionaryItem> queryPage(SystemDictionaryItemQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = dao.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<SystemDictionaryItem> rows = dao.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

}
