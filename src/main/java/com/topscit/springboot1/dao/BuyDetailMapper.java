package com.topscit.springboot1.dao;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.BuyDetail;

public interface BuyDetailMapper {
    int deleteByPrimaryKey(String bdid);

    int insert(BuyDetail record);

    int insertSelective(BuyDetail record);

    BuyDetail selectByPrimaryKey(String bdid);

    int updateByPrimaryKeySelective(BuyDetail record);

    int updateByPrimaryKey(BuyDetail record);
    
    BuyDetail getBuyDetailBy(@Param("bid")String id);
}