package com.topscit.springboot1.service;

import java.util.List;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.User;

public interface LoginService {

	User Select(String name);
	
	List<Permission> select(String id);
	
}
