package com.topscit.springboot1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/getAllBuyDetail")
	@ResponseBody
	public ResultBean getAllBuyDetail(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data){
		PageInfo<BuyDetail> selectBuyDetailList = buyservice.selectBuyDetailList(pn, size);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyDetailList);
		return resultBean;
	}
	
	@RequestMapping("/getAllBuyIn")
	@ResponseBody
	public ResultBean getAllBuyIn(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data){
		PageInfo<Buy> selectBuyList = buyservice.selectBuyInList(pn, size);
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
	
	
	@RequestMapping("/getBuyBybid")
	@ResponseBody
	public ResultBean getBuyBybid(String bid){
		Buy buyBybid = buyservice.getBuyBybid(bid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", buyBybid);
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
	
	@RequestMapping("/addInfo")
	@ResponseBody
	public ResultBean addInfo(@RequestBody Buy buy){
		boolean addBuy = buyservice.addBuy(buy);
		boolean addBuyDetail = buyservice.addBuyDetail(buy.getBuyDetail());
		if(addBuy && addBuyDetail){
			return new ResultBean(ResultBean.STATA_SUCCESS, "添加成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "添加失败");
		}
	}
	
	@RequestMapping("/delBuy")
	@ResponseBody
	public ResultBean delBuy(String[] bid){
		
		boolean deleteBuy = false;
		boolean deleteBuyDetailByBid = false;
		
		for(int i=0;i < bid.length;i++){
			deleteBuy = buyservice.deleteBuy(bid[i]);
			deleteBuyDetailByBid = buyservice.deleteBuyDetailByBid(bid[i]);
		}
		
		if(deleteBuy && deleteBuyDetailByBid){
			return new ResultBean(ResultBean.STATA_SUCCESS, "删除成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "删除失败");
		}
	}
	
	@RequestMapping("/getBuyDetailByTime")
	@ResponseBody
	public ResultBean getBuyDetailByTime(
			String beginDate,
			String endDate,
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data){
		List<BuyDetail> selectBuyDetailByTime = buyservice.selectBuyDetailByTime(beginDate, endDate);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyDetailByTime);
		return resultBean;
	}
	
	@RequestMapping("/getBuyByBid")
	@ResponseBody
	public ResultBean getBuyByBid(String bid){
		Buy buyBybid = buyservice.getBuyBybid(bid);
		BuyDetail buyDetailBy = buyservice.getBuyDetailBy(bid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", buyBybid,buyDetailBy);
		return resultBean;
	}
	
	@RequestMapping("/updateByBid")
	@ResponseBody
	public ResultBean updateByBid(@RequestBody Buy buy){
		
		boolean updateBuyByBid = buyservice.updateBuyByBid(buy);
		boolean updateBuyDetail = buyservice.updateBuyDetail(buy.getBuyDetail());
		if(updateBuyByBid && updateBuyDetail){
			return new ResultBean(ResultBean.STATA_SUCCESS, "修改成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "修改失败");
		}
	}
	
	
	@RequestMapping("/updateState")
	@ResponseBody
	public ResultBean updateState(String pid,String pcount,String bid){
		
		boolean updateCount = buyservice.updateCount(pcount, pid);
		boolean updateStateByBid = buyservice.updateStateByBid(bid);
		
		if(updateCount && updateStateByBid){
			return new ResultBean(ResultBean.STATA_SUCCESS, "修改成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "修改失败");
		}	}
	
	
	@RequestMapping("/getPartsBy")
	@ResponseBody
	public ResultBean getPartsBy(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data){
		PageInfo<Parts> selectPartsListBy = buyservice.selectPartsListBy(pn, size);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectPartsListBy);
		return resultBean;
	}
}

