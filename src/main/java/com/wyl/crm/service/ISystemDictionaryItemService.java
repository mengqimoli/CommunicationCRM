package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.SystemDictionaryItemQuery;
import com.wyl.crm.utils.PageResult;

public interface ISystemDictionaryItemService {
	
	void save(SystemDictionaryItem obj);
	
	void update(SystemDictionaryItem obj);
	
	void delete(Long id);
	
	SystemDictionaryItem getOne(Long id);
	
	List<SystemDictionaryItem> getAll();
	
	PageResult<SystemDictionaryItem> queryPage(SystemDictionaryItemQuery query);


}
