package com.topscit.springboot1.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.OrderGoods;

public interface OrderService {
	
		//查询OrderGoods表全部数据
		//用PageHelper对OrderGoods全部数据进行分页
		PageInfo<OrderGoods> selectListOrderGoodsByPn(String uid,int pn , int size);

		int deleteByPrimaryKey(String oid);
		
		int updateOgcountByOid(String oid , String count);

		void buyOrderGoods(String[] list,String cid);
}
