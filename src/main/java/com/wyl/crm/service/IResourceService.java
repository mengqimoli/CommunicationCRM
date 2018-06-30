package com.wyl.crm.service;

import java.util.List;
import java.util.Map;

import com.wyl.crm.domain.Permission;
import com.wyl.crm.domain.Resource;
import com.wyl.crm.query.ResourceQuery;
import com.wyl.crm.utils.PageResult;

public interface IResourceService {

	void save(Resource resource);

	void update(Resource resource);

	void delete(Long id);

	Resource getOne(Long id);

	List<Resource> getAll();

	/**
	 * 初始化模块信息（建立缓存）
	 */
	void initControllers();

	/**
	 * 获取控制器
	 * 
	 * @return
	 */
	Map<String, List<String>> getControllers();

	/**
	 * 导入模块资源
	 * 
	 * @param moudle
	 */
	void importControllerResources(String moudle);

	// 分页集合查询
	PageResult<Resource> queryPage(ResourceQuery query);

	// 资源加入权限
	void importPermission(String moudle);

}
