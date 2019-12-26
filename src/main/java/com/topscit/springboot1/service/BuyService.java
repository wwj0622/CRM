package com.topscit.springboot1.service;


import java.util.List;


import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Parts;

public interface BuyService {
	
	public PageInfo<Buy> selectBuyList(int pn,int size);
	
	public PageInfo<BuyDetail> selectBuyDetailList(int pn,int size);
	
	public List<Buy> selectBuyInList();
	
	public List<Buy> selectBuyByTime(String beginDate,String endDate);
	
	public List<BuyDetail> selectBuyDetailByTime(String beginDate,String endDate);
	
	 List<BuyDetail> getBuyDetailBy(String id);
	 
	 List<BuyDetail> getAllBuyDetailIn(String bid);
	 
	 public BuyDetail  getBuyDetailByBdid(String bdid);
	 
	 
	 //查询原材料名称
	 public List<Parts> getParts();
	 
	 //插入buy信息
	 public boolean addBuy(Buy buy);
	 
	 //插入buyDetail信息
	 public boolean addBuyDetail(BuyDetail buyDetail);
	 
	 //根据bid删除 buyDetail
	 public boolean deleteBuyDetailByBid(String id);
	 
	 public boolean deleteBuyDetail(String bdid);
	 
	 //根据bid删除 buy
	 public boolean deleteBuy(String id);
	 
	 //根据bid查询
	 public  Buy getBuyBybid(String bid);
	 
	 //修改buy
	 public boolean updateBuyByBid(Buy buy);
	 
	 //修改buyDetail
	 public boolean updateBuyDetail(BuyDetail buydetail);
	 
	 //修改出库状态
	 public boolean updateStateByBdid(String bdid);
	 
	 //修改原材料数量
	 public boolean updateCount(String count,String pid);
	 //查询缺货的goods
	 public PageInfo<Parts> selectPartsListBy(int pn,int size);
	 
	
	 
	 
}
