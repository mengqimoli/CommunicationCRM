package com.wyl.crm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wyl.crm.domain.PotentialCustomer;
import com.wyl.crm.domain.SystemDictionaryItem;
import com.wyl.crm.domain.SystemDictionaryType;
import com.wyl.crm.domain.SystemMenu;
import com.wyl.crm.mapper.PotentialCustomerMapper;
import com.wyl.crm.mapper.SystemDictionaryItemMapper;
import com.wyl.crm.query.PotentialCustomerQuery;
import com.wyl.crm.query.SystemMenuQuery;
import com.wyl.crm.service.IPotentialCustomerService;
import com.wyl.crm.utils.ExcelUtil;
import com.wyl.crm.utils.PageResult;
import com.wyl.crm.utils.UserContext;

@Service
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {

	@Autowired
	private PotentialCustomerMapper mapper;
	
	@Autowired
	private SystemDictionaryItemMapper dictionaryItemMapper;

	@Override
	public void save(PotentialCustomer obj) {
		mapper.save(obj);
	}

	@Override
	public void update(PotentialCustomer obj) {
		mapper.update(obj);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}
	
	@Override
	public void deleteByName(String name) {
		mapper.deleteByName(name);
	}

	@Override
	public PotentialCustomer getOne(Long id) {
		return mapper.getOne(id);
	}

	@Override
	public List<PotentialCustomer> getAll() {
		return mapper.getAll();
	}

	@Override
	public PageResult<PotentialCustomer> queryPage(PotentialCustomerQuery query) {
		PageResult pr = new PageResult();

		// 添加符合条件的总记录数
		Long total = mapper.queryCount(query);
		pr.setTotal(total);

		// 添加当前页显示的内容
		List<PotentialCustomer> rows = mapper.queryPage(query);
		pr.setRows(rows);

		return pr;
	}

	@Override
	public List<SystemDictionaryItem> getCustomerSource() {
		return mapper.getCustomerSource();
	}

	@Override
	public void importExcelInfo(InputStream in, MultipartFile file, String month, int adminId) throws Exception {
		
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
		List<PotentialCustomer> potentialCustomers = new ArrayList<PotentialCustomer>();
		
		// 遍历listob数据，把数据放到List中
		for (int i = 0; i < listob.size(); i++) {
			List<Object> ob = listob.get(i);
			PotentialCustomer potentialCustomer = new PotentialCustomer();
			// 通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
			potentialCustomer.setName(String.valueOf(ob.get(1)));
			potentialCustomer.setAge(Integer.valueOf((String) ob.get(2)));
			potentialCustomer.setSex(true);
			potentialCustomer.setLinkMan(String.valueOf(ob.get(4)));
			potentialCustomer.setLinkManTel(String.valueOf(ob.get(5)));
			potentialCustomer.setSuccessRate(String.valueOf(ob.get(6)));
			potentialCustomer.setIntro(String.valueOf(ob.get(7)));
			SystemDictionaryItem dictionaryItem = dictionaryItemMapper.getByName("客户转介绍");
			potentialCustomer.setCustomerSource(dictionaryItem);
			potentialCustomer.setInputTime(new Date());
			potentialCustomer.setInputUser(UserContext.getUser());
			
			potentialCustomers.add(potentialCustomer);
		}
		// 批量插入
		mapper.importPotentialCustomers(potentialCustomers);
	}

}
