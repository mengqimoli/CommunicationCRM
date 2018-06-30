package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.query.ContractQuery;
import com.wyl.crm.utils.PageResult;

public interface IContractService {

	void save(Contract obj);

	void delete(Long id);

	void update(Contract obj);

	Contract getOne(Long id);

	// 查询所有
	List<Contract> getAll();

	// 分页集合查询
	PageResult<Contract> queryPage(ContractQuery query);

}
