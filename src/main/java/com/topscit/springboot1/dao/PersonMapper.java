package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Person;

public interface PersonMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}