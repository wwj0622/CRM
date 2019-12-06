package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Buy;

public interface BuyMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Buy record);

    int insertSelective(Buy record);

    Buy selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Buy record);

    int updateByPrimaryKey(Buy record);
}