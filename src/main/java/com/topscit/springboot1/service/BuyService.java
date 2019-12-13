package com.topscit.springboot1.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Parts;

public interface BuyService {
	
	public PageInfo<Buy> selectBuyList(int pn,int size);
	
	public PageInfo<Buy> selectBuyByTime(String beginDate,String endDate,int pn,int size);
	
	 BuyDetail getBuyDetailBy(String id);
	 
	 //查询原材料名称
	 public List<Parts> getParts();
	 
	 //插入buy信息
	 public boolean addBuy(Buy buy);
	 
	 //插入buyDetail信息
	 public boolean addBuyDetail(BuyDetail buyDetail);
	 
	 //根据bid删除 buyDetail
	 public boolean deleteBuyDetailByBid(String id);
	 
	 //根据bid删除 buy
	 public boolean deleteBuy(String id);
	 
	 
	 
	 
	 
}
