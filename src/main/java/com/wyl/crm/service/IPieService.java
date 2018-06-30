package com.wyl.crm.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.wyl.crm.domain.Customer;
import com.wyl.crm.domain.Employee;
import com.wyl.crm.domain.Pie;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.query.CustomerQuery;
import com.wyl.crm.query.PieQuery;
import com.wyl.crm.utils.PageResult;

public interface IPieService {

	//客户服务级别
	List<Pie> getPie(PieQuery qo);

	//客户状态
	List<Pie> getPieByCustomerStatus(PieQuery qo);

	//客户来源
	List<Pie> getPieByCustomerSource(PieQuery qo);


}
