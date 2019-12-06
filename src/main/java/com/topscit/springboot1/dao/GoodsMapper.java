package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}