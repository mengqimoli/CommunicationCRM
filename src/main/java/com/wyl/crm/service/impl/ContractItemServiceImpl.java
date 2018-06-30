package com.wyl.crm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Contract;
import com.wyl.crm.domain.ContractItem;
import com.wyl.crm.exception.LogicException;
import com.wyl.crm.mapper.ContractItemMapper;
import com.wyl.crm.mapper.ContractMapper;
import com.wyl.crm.query.ContractItemQuery;
import com.wyl.crm.service.IContractItemService;
import com.wyl.crm.utils.AjaxResult;
import com.wyl.crm.utils.PageResult;

/*
 * 合同扩展类
 */
@Service
public class ContractItemServiceImpl implements IContractItemService {
	@Autowired
	private ContractItemMapper mapper;
	@Autowired
	private ContractMapper contractMapper;

	@Override
	public void save(ContractItem contractItem) {
		if (contractItem.getContract().getId() != null) {
			Contract contract = contractMapper.getOne(contractItem.getContract().getId());
			String scale = contractItem.getMoney().divide(contract.getSum(), 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal("100")).toString() + "%";
			contractItem.setScale(scale);
			mapper.save(contractItem);
		} else {
			throw new LogicException("请选中一个合同来添加其明细", -100);
		}
	}

	@Override
	public void update(ContractItem contractItem) {
		if (contractItem.getContract().getId() != null) {
			Contract contract = contractMapper.getOne(contractItem.getContract().getId());
			String scale = contractItem.getMoney().divide(contract.getSum(), 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal("100")).toString() + "%";
			contractItem.setScale(scale);
			mapper.update(contractItem);
		} else {
			throw new LogicException("请选中一个合同来编辑其明细", -100);
		}
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Override
	public PageResult<ContractItem> queryPage(ContractItemQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<ContractItem> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	// 保存合同明细
	/*
	 * @Override public void saveItem(Contract contract) { // 拿到合同明细
	 * List<ContractItem> contractItems = contract.getContractItem(); for
	 * (ContractItem contractItem : contractItems) { // 合同金额所占比例 String scale =
	 * contractItem.getMoney().divide(contract.getSum(), 2,
	 * RoundingMode.HALF_UP) .multiply(new BigDecimal("100")).toString() + "%";
	 * contractItem.setScale(scale); contractItem.setContract(contract);
	 * mapper.save(contractItem); } }
	 */
	// 计算比例的测试
	/*
	 * public static void main(String[] args) { BigDecimal a = new
	 * BigDecimal("1000"); BigDecimal b = new BigDecimal("2000"); String
	 * residual = a.divide(b).multiply(new BigDecimal("100")).toString()+"%";
	 * System.out.println(residual); }
	 */

	@Override
	public List<ContractItem> getItem(Long id) {
		return mapper.getItem(id);
	}

	@Override
	public void deleteByContractId(Long id) {
		mapper.deleteByContractId(id);
	}

}
