package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(String sid);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}