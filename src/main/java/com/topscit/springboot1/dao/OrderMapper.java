package com.topscit.springboot1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectAllOrderByKid(@Param("id")String id);
    
    int updateOsumByOid(@Param("oid")String oid,@Param("money")String money); 
    
    int updateOstateOid(@Param("oid")String oid); 
}