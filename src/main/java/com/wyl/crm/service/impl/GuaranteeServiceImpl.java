package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Guarantee;
import com.wyl.crm.mapper.GuaranteeMapper;
import com.wyl.crm.query.GuaranteeQuery;
import com.wyl.crm.service.IGuaranteeService;
import com.wyl.crm.utils.PageResult;

@Service
public class GuaranteeServiceImpl implements IGuaranteeService {
	@Autowired
	private GuaranteeMapper mapper;

	@Override
	public void save(Guarantee obj) {
		mapper.save(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public void update(Guarantee guarantee) {
		mapper.update(guarantee);
	}

	@Override
	public Guarantee getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<Guarantee> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<Guarantee> queryPage(GuaranteeQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Guarantee> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

}
