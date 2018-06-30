package com.wyl.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyl.crm.domain.Pie;
import com.wyl.crm.query.PieQuery;
import com.wyl.crm.service.IPieService;
import com.wyl.crm.utils.Resource;

@Controller
@RequestMapping("/pie")
@Resource("数据统计管理")
public class PieController {

	@Autowired
	private IPieService pieServiceImpl;

	@Resource("数据统计页面导向")
	@RequestMapping("/index")
	public String index() {
		return "pie/pie";
	}

	@RequestMapping("/getPie")
	@Resource("数据统计客户服务级别")
	@ResponseBody
	public List<Pie> getPie(PieQuery qo) {
		// 获取饼图数据
		return pieServiceImpl.getPie(qo);
	}

	@RequestMapping("/getPieByCustomerStatus")
	@Resource("数据统计客户状态")
	@ResponseBody
	public List<Pie> getPieByCustomerStatus(PieQuery qo) {
		// 获取饼图数据
		return pieServiceImpl.getPieByCustomerStatus(qo);
	}

	@RequestMapping("/getPieByCustomerSource")
	@Resource("数据统计客户来源")
	@ResponseBody
	public List<Pie> getPieByCustomerSource(PieQuery qo) {
		// 获取饼图数据
		return pieServiceImpl.getPieByCustomerSource(qo);
	}

}
