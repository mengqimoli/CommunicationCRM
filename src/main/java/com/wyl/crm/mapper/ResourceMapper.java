package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Resource;
import com.wyl.crm.query.ResourceQuery;

public interface ResourceMapper {

	void createTable();

	void save(Resource obj);

	void update(Resource obj);

	void delete(Long id);

	Resource getOne(Long id);

	List<Resource> getAll();

	List<Resource> queryPage(ResourceQuery query);

	Long queryCount(ResourceQuery query);

	List<Resource> getByUrl(String url);
}
