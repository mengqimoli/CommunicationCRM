package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.query.ContractItemQuery;

public interface ContractItemMapper {

	void save(ContractItem t);

	void update(ContractItem t);

	void delete(Long id);

	List<ContractItem> queryPage(ContractItemQuery query);

	Long queryCount(ContractItemQuery query);

	// 根据合同获取明细
	List<ContractItem> getItem(Long id);

	//删除合同的时候删除明细
	void deleteByContractId(Long id);

}
