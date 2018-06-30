package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.SystemLog;
import com.wyl.crm.mapper.SystemLogMapper;
import com.wyl.crm.query.SystemLogQuery;
import com.wyl.crm.service.ISystemLogService;
import com.wyl.crm.utils.PageResult;

@Service(value = "systemLogServiceImpl")
public class SystemLogServiceImpl implements ISystemLogService {

	private SystemLogMapper mapper;

	@Autowired
	public void setMapper(SystemLogMapper mapper) {
		// 由Spring注入mapper
		this.mapper = mapper;

		// 自动建表
		mapper.createTable();
	}

	@Override
	public void save(SystemLog obj) {
		mapper.save(obj);
	}

	@Override
	public void update(SystemLog obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public SystemLog getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<SystemLog> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<SystemLog> queryPage(SystemLogQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<SystemLog> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	//删除全部
	@Override
	public void deleteAll(List<Long> list) {
		mapper.deleteAll(list);
	}

}
