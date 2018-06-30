package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.SystemLog;
import com.wyl.crm.query.SystemLogQuery;
import com.wyl.crm.utils.PageResult;

public interface ISystemLogService {

	void save(SystemLog obj);

	void delete(Long id);

	void update(SystemLog obj);

	SystemLog getOne(Long id);

	//查询所有
	List<SystemLog> getAll();

	// 分页集合查询
	PageResult<SystemLog> queryPage(SystemLogQuery qo);

	//删除全部
	void deleteAll(List<Long> list);

}
