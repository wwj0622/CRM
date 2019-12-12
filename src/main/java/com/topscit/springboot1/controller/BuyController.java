package com.topscit.springboot1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.bean.ResultBean;
import com.topscit.springboot1.bean.Supplier;
import com.topscit.springboot1.dao.SupplierMapper;
import com.topscit.springboot1.service.BuyService;

@Controller
@RequestMapping("/buy")
public class BuyController {

	@Resource
	private BuyService buyservice;
	
	@Resource
	private SupplierMapper supplierMapper;
	
	@RequestMapping("/getAllBuy")
	@ResponseBody
	public ResultBean getAllBuy(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data){
		PageInfo<Buy> selectBuyList = buyservice.selectBuyList(pn, size);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyList);
		return resultBean;
	}
	
	@RequestMapping("/getBuyDetailBybid")
	@ResponseBody
	public ResultBean getBuyDetailBybid(String bid){
		BuyDetail buyDetailBy = buyservice.getBuyDetailBy(bid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", buyDetailBy);
		return resultBean;
	}
	
	
	@RequestMapping("/getInfo")
	@ResponseBody
	public ResultBean getInfo(){
		List<Parts> parts = buyservice.getParts();
		List<Supplier> allSupplier = supplierMapper.getAllSupplier();
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", parts,allSupplier);
		return resultBean;
	}
}
