package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.query.ContractItemQuery;
import com.wyl.crm.query.ContractQuery;
import com.wyl.crm.utils.PageResult;

public interface IContractItemService {

	void save(ContractItem obj);

	void delete(Long id);

	void update(ContractItem obj);

	// 分页集合查询
	PageResult<ContractItem> queryPage(ContractItemQuery query);

	// 获取合同明细
	List<ContractItem> getItem(Long id);

	void deleteByContractId(Long id);

}
