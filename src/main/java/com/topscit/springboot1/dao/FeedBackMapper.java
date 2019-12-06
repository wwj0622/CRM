package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.FeedBack;

public interface FeedBackMapper {
    int deleteByPrimaryKey(String fid);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
}