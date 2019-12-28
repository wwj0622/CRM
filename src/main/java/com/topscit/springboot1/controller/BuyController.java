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
	public ResultBean getAllBuyIn(){
		List<Buy> selectBuyList = buyservice.selectBuyInList();
		List<BuyDetail> buyDetail = selectBuyList.get(0).getBuyDetail();
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyList,buyDetail);
		return resultBean;
	}
	
	@RequestMapping("/getAllBuyDetailIn")
	@ResponseBody
	public ResultBean getAllBuyDetailIn(String bid){
		List<BuyDetail> allBuyDetailIn = buyservice.getAllBuyDetailIn(bid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", allBuyDetailIn);
		return resultBean;
	}
	
//	查询buyDetailList
	@RequestMapping("/getBuyDetailBybid")
	@ResponseBody
	public ResultBean getBuyDetailBybid(String bid){
		List<BuyDetail> buyDetailBy = buyservice.getBuyDetailBy(bid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", buyDetailBy);
		return resultBean;
	}
	
		
	@RequestMapping("/getBuyDetailByBdid")
	@ResponseBody
	public ResultBean getBuyDetailByBdid(String bdid){
		BuyDetail buyDetailByBdid = buyservice.getBuyDetailByBdid(bdid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", buyDetailByBdid);
		return resultBean;
	}
	
	@RequestMapping("/getBuyByBid")
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
		System.out.println(parts);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", parts,allSupplier);
		return resultBean;
	}
	
	@RequestMapping("/addInfo")
	@ResponseBody
	public ResultBean addInfo(Buy buy){
		boolean addBuy = buyservice.addBuy(buy);
		String bid = buy.getBid();
		if(addBuy){
			return new ResultBean(ResultBean.STATA_SUCCESS, "添加成功",bid);
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "添加失败");
		}
	}
	
	
	@RequestMapping("/addBuyDetail")
	@ResponseBody
	public ResultBean addBuyDetail(@RequestBody String[] buyDetail){
		BuyDetail buyDetail2 = new BuyDetail();
		String[] count =new String[buyDetail.length/4];
		String[] price =new String[buyDetail.length/4];
		String[] bid =new String[buyDetail.length/4];
		String[] pid =new String[buyDetail.length/4];
		for(int j=0;j<buyDetail.length/4;j++){
			for (int i = j*4; i < (j+1)*4; i++) {
				if(i==0){
					bid[j]=buyDetail[i];
				}
				else{
					if(i%4==0){
						bid[j]=buyDetail[i];
						
					}
					if((i+1)%4==0){
						pid[j]=buyDetail[i];
					}
					if((i+2)%4==0){
						price[j]=buyDetail[i];
					}
					if((i+3)%4==0){
						count[j]=buyDetail[i];
					}
				}
			}
			
		}
		for(int i=0;i<buyDetail.length/4;i++){
			buyDetail2.setBuyId(bid[i]);
			buyDetail2.setBdcount(count[i]);
			buyDetail2.setBdprice(price[i]);
			buyDetail2.setPartsId(pid[i]);
			buyservice.addBuyDetail(buyDetail2);
		}
		return new ResultBean(ResultBean.STATA_SUCCESS, "添加成功");
	}
	
	@RequestMapping("/delBuy")
	@ResponseBody
	public ResultBean delBuy(String[] bid){
		
		boolean deleteBuy = false;
		boolean deleteBuyDetailByBid = false;
		
		for(int i=0;i < bid.length;i++){
			deleteBuy = buyservice.deleteBuy(bid[i]);
			for(int j=0;j < bid.length;j++){
				deleteBuyDetailByBid = buyservice.deleteBuyDetailByBid(bid[j]);
			}
		}
		
		if(deleteBuy && deleteBuyDetailByBid){
			return new ResultBean(ResultBean.STATA_SUCCESS, "删除成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "删除失败");
		}
	}
	
	
	
	
	@RequestMapping("/delBuyDetail")
	@ResponseBody
	public ResultBean delBuyDetail(String[] bdid){
		
		boolean deleteBuyDetail = false;
		
		for(int i=0;i < bdid.length;i++){
			deleteBuyDetail = buyservice.deleteBuyDetail(bdid[i]);
		}
		
		if(deleteBuyDetail){
			return new ResultBean(ResultBean.STATA_SUCCESS, "删除成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "删除失败");
		}
	}
	@RequestMapping("/getBuyDetailByTime")
	@ResponseBody
	public ResultBean getBuyDetailByTime(String beginDate,String endDate){
		List<BuyDetail> selectBuyDetailByTime = buyservice.selectBuyDetailByTime(beginDate, endDate);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyDetailByTime);
		return resultBean;
	}
	
	@RequestMapping("/getBuyByTime")
	@ResponseBody
	public ResultBean getBuyByTime(String beginDate,String endDate){
		List<Buy> selectBuyByTime = buyservice.selectBuyByTime(beginDate, endDate);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", selectBuyByTime);
		return resultBean;
	}
	
	@RequestMapping("/updateByBid")
	@ResponseBody
	public ResultBean updateByBid(Buy buy){
		boolean updateBuyByBid = buyservice.updateBuyByBid(buy);
		if(updateBuyByBid){
			return new ResultBean(ResultBean.STATA_SUCCESS, "修改成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "修改失败");
		}
	}
	
	@RequestMapping("/updateBuyDetail")
	@ResponseBody
	public ResultBean updateBuyDetail(BuyDetail buyDetail){
		boolean updateBuyDetail = buyservice.updateBuyDetail(buyDetail);
		if(updateBuyDetail){
			return new ResultBean(ResultBean.STATA_SUCCESS, "修改成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "修改失败");
		}
	}
	
	//11111111111
	@RequestMapping("/updateState")
	@ResponseBody
	public ResultBean updateState(String[] bdid){
		boolean updateCount =false;
		boolean updateStateByBdid =false;
		for(int i=0;i < bdid.length;i++){
			
			BuyDetail buyDetailByBdid = buyservice.getBuyDetailByBdid(bdid[i]);
			String bdcount = buyDetailByBdid.getBdcount();
			String pid = buyDetailByBdid.getPartsId();
			
			updateCount = buyservice.updateCount(bdcount, pid);
			updateStateByBdid = buyservice.updateStateByBdid(bdid[i]);
		}
		if(updateCount && updateStateByBdid){
			return new ResultBean(ResultBean.STATA_SUCCESS, "修改成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "修改失败");
		}	
		}
	
	
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

