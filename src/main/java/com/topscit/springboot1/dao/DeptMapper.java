package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Dept;

public interface DeptMapper {
    int deleteByPrimaryKey(String did);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String did);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}