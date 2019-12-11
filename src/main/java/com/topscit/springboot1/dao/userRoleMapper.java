package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.userRole;

public interface userRoleMapper {
    int deleteByPrimaryKey(String id);
    
    int deletePower(String id);
    
    int insert(userRole record);

    int insertSelective(userRole record);

    userRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userRole record);

    int updateByPrimaryKey(userRole record);
    
    

    
    
}