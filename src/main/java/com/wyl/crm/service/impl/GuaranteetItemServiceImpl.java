package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.GuaranteeItem;
import com.wyl.crm.mapper.GuaranteeItemMapper;
import com.wyl.crm.query.GuaranteeItemQuery;
import com.wyl.crm.service.IGuaranteeItemService;
import com.wyl.crm.utils.PageResult;

/*
 * 合同扩展类
 */
@Service
public class GuaranteetItemServiceImpl implements IGuaranteeItemService {
	@Autowired
	private GuaranteeItemMapper mapper;

	@Override
	public void save(GuaranteeItem guaranteeItem) {
		mapper.save(guaranteeItem);
	}

	@Override
	public void update(GuaranteeItem guaranteeItem) {
		mapper.update(guaranteeItem);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public PageResult<GuaranteeItem> queryPage(GuaranteeItemQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<GuaranteeItem> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<GuaranteeItem> getItem(Long id) {
		return mapper.getItem(id);
	}

	@Override
	public List<Employee> getRepairer() {
		// 返回所有修理工的信息,可以选择
		return mapper.getRepairer();
	}

}
