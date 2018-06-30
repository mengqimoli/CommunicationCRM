package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.SystemDictionaryItemQuery;

public interface SystemDictionaryItemMapper {

	void createTable();
	
	void save(SystemDictionaryItem obj);
	
	void update(SystemDictionaryItem obj);
	
	void delete(Long id);
	
	SystemDictionaryItem getOne(Long id);
	
	List<SystemDictionaryItem> getAll();

	List<SystemDictionaryItem> queryPage(SystemDictionaryItemQuery query);

	Long queryCount(SystemDictionaryItemQuery query);

	SystemDictionaryItem getByName(String string);
}
