package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Supplier;

public interface SupplierMapper {
    int deleteByPrimaryKey(String sid);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
    
    
    public List<Supplier> getAllSupplier();
    
    public List<Supplier> getSupplierBy(String name);
}