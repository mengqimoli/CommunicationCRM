package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.GuaranteeItem;
import com.wyl.crm.query.GuaranteeItemQuery;

public interface GuaranteeItemMapper {

	void save(GuaranteeItem t);

	void update(GuaranteeItem t);

	void delete(Long id);

	List<GuaranteeItem> queryPage(GuaranteeItemQuery query);

	Long queryCount(GuaranteeItemQuery query);

	// 根据合同获取明细
	List<GuaranteeItem> getItem(Long id);

	List<Employee> getRepairer();

}
