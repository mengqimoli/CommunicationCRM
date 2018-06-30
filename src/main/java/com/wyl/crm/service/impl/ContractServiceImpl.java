package com.wyl.crm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.mapper.ContractItemMapper;
import com.wyl.crm.mapper.ContractMapper;
import com.wyl.crm.query.ContractQuery;
import com.wyl.crm.service.IContractService;
import com.wyl.crm.utils.PageResult;

@Service
public class ContractServiceImpl implements IContractService {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private ContractItemMapper contractItemMapper;

	@Override
	public void save(Contract obj) {
		contractMapper.save(obj);
	}

	@Override
	public void delete(Long id) {
		contractMapper.delete(id);
	}

	@Override
	public void update(Contract contract) {
		contractMapper.update(contract);
		// //先删除合同
		// contractItemMapper.remove(contract.getId());
		// 拿到所有明细
		List<ContractItem> contractItems = contract.getContractItem();
		for (ContractItem contractItem : contractItems) {
			// 合同金额所占比例
			String residual = contractItem.getMoney().divide(contract.getSum(), 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal("100")).toString() + "%";
			contractItem.setScale(residual);
			contractItem.setContract(contract);
			contractItemMapper.update(contractItem);
		}
	}

	@Override
	public Contract getOne(Long id) {
		return contractMapper.getOne(id);
	}

	@Override
	public List<Contract> getAll() {
		return contractMapper.getAll();
	}

	@Override
	public PageResult<Contract> queryPage(ContractQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = contractMapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<Contract> rows = contractMapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

}
