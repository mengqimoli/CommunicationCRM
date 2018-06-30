package com.wyl.crm.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.PotentialCustomerQuery;
import com.wyl.crm.utils.PageResult;

public interface IPotentialCustomerService {

	void save(PotentialCustomer obj);

	void delete(Long id);

	void update(PotentialCustomer obj);

	PotentialCustomer getOne(Long id);

	// 查询所有
	List<PotentialCustomer> getAll();

	// 分页集合查询
	PageResult<PotentialCustomer> queryPage(PotentialCustomerQuery query);

	//获取用户资源
	List<SystemDictionaryItem> getCustomerSource();

	void importExcelInfo(InputStream in, MultipartFile file, String month, int adminId) throws Exception;

	void deleteByName(String name);
}
