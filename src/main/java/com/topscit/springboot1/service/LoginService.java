package com.topscit.springboot1.service;

import java.util.HashMap;
import java.util.List;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.bean.userRole;

public interface LoginService {

	User Select(String name);
	
	List<Permission> select(String id);
	
	Boolean  addUser(User user);
	
	User selectName(User user);
	
	List<Role> selectRole();

	Role selectR(String id);
	
	boolean insertRole(userRole user);
	
	User selectUser(String id);
	
	boolean testUser(String pass3);
	
	List<User> selectUser();
	
	List<User> selectDUser();
	
	boolean StopUser(User user);
	
	boolean deleteUser(User user);
	 
	User selectID(User user);
	
	User IdUser(String id);
	
	Role RoleName(String id);
	
	boolean deletePower(String id);
	
	boolean updatePower(User user);
	
	boolean updateUserPassword(User user);
	
	List<User>  SelectUser(HashMap<String, String> data);
	
	List<User>  SelectDeleteUser(HashMap<String, String> data);
	
	boolean DeleteUser(String id);
	
	List<Role>  selectPermission(String id);
	
	List<User>  selectUserandRole();
	
	List<Permission> selecPermission();
	
	Role SelectRolePermission(String id);
}
