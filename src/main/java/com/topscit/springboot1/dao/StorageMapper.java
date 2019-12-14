package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Storage;

public interface StorageMapper {
    int deleteByPrimaryKey(String stid);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(String stid);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
    
    List<Storage> selectTid();
}