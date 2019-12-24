package com.topscit.springboot1.dao;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.partsGoods;

public interface partsGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(partsGoods record);

    int insertSelective(partsGoods record);

    partsGoods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(partsGoods record);

    int updateByPrimaryKey(partsGoods record);
    
    int DeleteGoodsParts(String id);
    
    int GoodsParts(@Param("pid")String pid,@Param("gid")String gid);
}