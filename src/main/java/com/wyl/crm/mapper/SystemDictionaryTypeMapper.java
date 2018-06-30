package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.query.SystemDictionaryTypeQuery;

public interface SystemDictionaryTypeMapper {

	void createTable();
	
	void save(SystemDictionaryType obj);
	
	void delete(Long id);

	void update(SystemDictionaryType obj);
	
	SystemDictionaryType getOne(Long id);
	
	List<SystemDictionaryType> getAll();

	List<SystemDictionaryType> queryPage(SystemDictionaryTypeQuery query);

	Long queryCount(SystemDictionaryTypeQuery query);
}
