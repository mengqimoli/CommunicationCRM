package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.query.ContractQuery;

public interface ContractMapper {

	void save(Contract t);

	void update(Contract t);

	void delete(Long id);

	Contract getOne(Long id);

	List<Contract> getAll();

	List<Contract> queryPage(ContractQuery query);

	Long queryCount(ContractQuery query);

}
