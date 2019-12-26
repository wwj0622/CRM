package com.topscit.springboot1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Order;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.Yorder;

public interface OrderService {
	
		//查询OrderGoods表全部数据
		//用PageHelper对OrderGoods全部数据进行分页
		PageInfo<OrderGoods> selectListOrderGoodsByPn(String uid,int pn , int size);

		int deleteByPrimaryKey(String oid);
		
		int deleteOrderGoodsByYoid(String yoid);
		
		int deleteOrderByPrimaryKey(String yoid);
		
		int insert(Yorder record);
		
		int updateOgcountByOid(String oid , String count);

		void buyOrderGoods(String[] list,String cid);
		
		void addOrderGoods(OrderGoods[] list);
		
		List<Order> selectAllOrderByKid();
		
		List<OrderGoods> selectOrderGoodsByYoid(String id);
		
		OrderGoods selectListGoodsOrderByOid(String oid);
		
		int updateOsumByOid(String oid,String yoid); 
		
		Order selectOrderByOid(String yoid);
		
		int updateOstateOid(String oid); 
		
		List<Yorder> selectAllYorder();
		
		List<OrderGoods> selectListGoodsOrderByYoid(String yoid);
}
