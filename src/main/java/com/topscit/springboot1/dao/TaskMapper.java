package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}