package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.Salesman;

public interface SalesmanMapper {
    int deleteByPrimaryKey(String smid);

    int insert(Salesman record);

    int insertSelective(Salesman record);

    Salesman selectByPrimaryKey(String smid);

    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}