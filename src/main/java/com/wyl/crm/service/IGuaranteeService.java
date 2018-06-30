package com.wyl.crm.service;

import java.util.List;

import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.query.GuaranteeQuery;
import com.wyl.crm.utils.PageResult;

public interface IGuaranteeService {

	void save(Guarantee obj);

	void delete(Long id);

	void update(Guarantee obj);

	Guarantee getOne(Long id);

	// 查询所有
	List<Guarantee> getAll();

	// 分页集合查询
	PageResult<Guarantee> queryPage(GuaranteeQuery query);

}
