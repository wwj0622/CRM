package com.topscit.springboot1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.Storage;

public interface GoodsService {
	
	//查询goods表全部数据
	//用PageHelper对goods全部数据进行分页
	PageInfo<Goods> selectListGoodsByPn(int pn , int size);
	
	//删除多条信息
	void delGoodsById(String[] gids);
	
	//模糊查询goods表全部数据
	//用PageHelper对goods全部数据进行分页
	PageInfo<Goods> selectListGoodsByLimit(String gname,int pn , int size);

	//修改goods信息
	int updateByPrimaryKey(Goods goods);
	
	//添加goods
	int insertSelective(Goods goods);
	
	//查询所有的仓库编号
	List<String> selectTid();
	
	//添加orderGoods订单
	int insert(OrderGoods record);
	
	//根据客户id查共有多少条订单
	int selectAllOrderById(String id);
	
	//查出所有的以购买的商品id
	List<OrderGoods> selectByGid(String uid);
	
	//订单里已有该商品，就添加数量
	int updateGtOgcount(String gid , String count);
}
