package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User select(String name);
	
	User selectUser(String id);
	
	List<User> AllUser();
	
	List<User> AllDeleteUser();
	
}