package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.query.SystemDictionaryTypeQuery;
import com.wyl.crm.utils.PageResult;

public interface ISystemDictionaryTypeService {

	void save(SystemDictionaryType obj);

	void delete(Long id);

	void update(SystemDictionaryType obj);

	SystemDictionaryType getOne(Long id);

	List<SystemDictionaryType> getAll();

	// 分页集合查询
	PageResult<SystemDictionaryType> queryPage(SystemDictionaryTypeQuery query);

}
