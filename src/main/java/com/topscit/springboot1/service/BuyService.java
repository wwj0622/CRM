package com.topscit.springboot1.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Parts;

public interface BuyService {
	
	public PageInfo<Buy> selectBuyList(int pn,int size);
	
	 BuyDetail getBuyDetailBy(String id);
	 
	 //查询原材料名称
	 public List<Parts> getParts();
}
