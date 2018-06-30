package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.PotentialCustomerQuery;

public interface PotentialCustomerMapper {

	void save(PotentialCustomer t);

	void update(PotentialCustomer t);

	void delete(Long id);

	PotentialCustomer getOne(Long id);

	List<PotentialCustomer> getAll();

	List<PotentialCustomer> queryPage(PotentialCustomerQuery query);

	Long queryCount(PotentialCustomerQuery query);

	//获取用户来源
	List<SystemDictionaryItem> getCustomerSource();

	void importPotentialCustomers(List<PotentialCustomer> potentialCustomers);

	void deleteByName(String name);

	PotentialCustomer getByPoteName(String poteName);
}
