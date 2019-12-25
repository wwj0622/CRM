package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Yorder;

public interface YorderMapper {
    int deleteByPrimaryKey(String yoid);

    int insert(Yorder record);

    int insertSelective(Yorder record);

    Yorder selectByPrimaryKey(String yoid);

    int updateByPrimaryKeySelective(Yorder record);

    int updateByPrimaryKey(Yorder record);
    
    List<Yorder> selectAllYorder();
}