package com.wyl.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyl.crm.domain.Pie;
import com.wyl.crm.mapper.PieMapper;
import com.wyl.crm.query.PieQuery;
import com.wyl.crm.service.IPieService;

@Service
public class PieServiceImpl implements IPieService {

	@Autowired
	private PieMapper mapper;

	@Override
	public List<Pie> getPie(PieQuery qo) {
		return mapper.getPie(qo);
	}

	@Override
	public List<Pie> getPieByCustomerStatus(PieQuery qo) {
		return mapper.getPieByCustomerStatus(qo);
	}

	@Override
	public List<Pie> getPieByCustomerSource(PieQuery qo) {
		return mapper.getPieByCustomerSource(qo);
	}


}
