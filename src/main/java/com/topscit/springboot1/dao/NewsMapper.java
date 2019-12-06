package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.News;

public interface NewsMapper {
    int deleteByPrimaryKey(String nid);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String nid);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}