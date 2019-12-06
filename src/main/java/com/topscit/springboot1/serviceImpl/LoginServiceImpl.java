package com.topscit.springboot1.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.dao.PermissionMapper;
import com.topscit.springboot1.dao.UserMapper;
import com.topscit.springboot1.service.LoginService;


@Service("/loginService")
public class LoginServiceImpl implements LoginService{

	@Resource
	private UserMapper userMapper;
	
	
	@Resource
	private PermissionMapper permissionMapper;
	
	@Override
	public User Select(String name) {
        User select = userMapper.select(name);
		
		return select;
	}

	@Override
	public List<Permission> select(String id) {

	    List<Permission> select = permissionMapper.select(id);
		
		return select;
	}

}
