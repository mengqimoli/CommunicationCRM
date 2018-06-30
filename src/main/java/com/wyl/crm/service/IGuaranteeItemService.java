package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.GuaranteeItem;
import com.wyl.crm.query.GuaranteeItemQuery;
import com.wyl.crm.utils.PageResult;

public interface IGuaranteeItemService {

	void save(GuaranteeItem obj);

	void delete(Long id);

	void update(GuaranteeItem obj);

	// 分页集合查询
	PageResult<GuaranteeItem> queryPage(GuaranteeItemQuery query);

	// 获取合同明细
	List<GuaranteeItem> getItem(Long id);

	List<Employee> getRepairer();

}
