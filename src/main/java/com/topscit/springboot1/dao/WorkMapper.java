package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Work;

public interface WorkMapper {
    int deleteByPrimaryKey(String wid);

    int insert(Work record);

    int insertSelective(Work record);

    Work selectByPrimaryKey(String wid);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}