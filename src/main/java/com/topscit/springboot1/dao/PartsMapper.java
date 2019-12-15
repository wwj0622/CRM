package com.topscit.springboot1.dao;


import java.util.List;

import com.topscit.springboot1.bean.Parts;

public interface PartsMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Parts record);

    int insertSelective(Parts record);

    Parts selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Parts record);

    int updateByPrimaryKey(Parts record);
    
    List<Parts> getAllParts();
}