package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.User;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRole();
    
    int insertRole(User user);
    
    Role RoleName(String id);
    
    Role selectRolename(String id);
    
    Role SelectRP(String id);
    
 
    
    
}