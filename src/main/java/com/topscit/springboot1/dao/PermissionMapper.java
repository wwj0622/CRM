package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> select(String id);
    
    List<Role> selectPermission(String id);
    
    List<Permission> selectall();
    
    
    
    
}