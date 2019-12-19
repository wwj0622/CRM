package com.topscit.springboot1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<Customer> selectAllKehuById(@Param("smid")String smid);
    
}