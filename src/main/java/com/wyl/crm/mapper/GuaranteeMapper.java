package com.wyl.crm.mapper;

import java.util.List;

import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.query.GuaranteeQuery;

public interface GuaranteeMapper {

	void save(Guarantee t);

	void update(Guarantee t);

	void delete(Long id);

	Guarantee getOne(Long id);

	List<Guarantee> getAll();

	List<Guarantee> queryPage(GuaranteeQuery query);

	Long queryCount(GuaranteeQuery query);

}
