package com.topscit.springboot1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.OrderGoods;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(String oid);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(String oid);
    
    OrderGoods selectOrderGoodsByPrimaryKey(@Param("oid")String oid);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);
    
    int selectAllOrderById(@Param("id")String id);
    
    //查询所有订单中的商品id
    List<OrderGoods> selectByGid(@Param("uid")String uid);
    
    int updateGtOgcount(@Param("gid")String gid , @Param("count")String count);
    
    List<OrderGoods> selectListGoodsOrder(@Param("uid")String uid);
    List<OrderGoods> selectListGoodsOrderByYoid(@Param("yoid")String yoid);
    OrderGoods selectListGoodsOrderByOid(@Param("oid")String oid);
    int updateOgcountByOid(@Param("oid")String oid , @Param("count")String count);
    
    List<OrderGoods> selectOrderGoodsByYoid(@Param("id")String id);
    
    int deleteOrderGoodsByYoid(@Param("yoid")String yoid);
    
}