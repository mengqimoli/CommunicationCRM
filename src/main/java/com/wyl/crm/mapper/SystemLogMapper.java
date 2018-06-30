package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.SystemLog;
import com.wyl.crm.query.SystemLogQuery;

public interface SystemLogMapper {

	void createTable();

	void save(SystemLog obj);

	void delete(Long id);

	void update(SystemLog obj);

	SystemLog getOne(Long id);

	// 查询所有
	List<SystemLog> getAll();

	// 分页集合查询
	List<SystemLog> queryPage(SystemLogQuery query);

	// 获取分页查询总数
	Long queryCount(SystemLogQuery query);

	//删除全部
	void deleteAll(List<Long> list);
}
