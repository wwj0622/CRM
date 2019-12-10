package com.topscit.springboot1.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.bean.userRole;
import com.topscit.springboot1.dao.PermissionMapper;
import com.topscit.springboot1.dao.RoleMapper;
import com.topscit.springboot1.dao.RolePermissionMapper;
import com.topscit.springboot1.dao.UserMapper;
import com.topscit.springboot1.dao.userRoleMapper;
import com.topscit.springboot1.service.LoginService;
import com.topscit.springboot1.util.Md5Util;


@Service("/loginService")
public class LoginServiceImpl implements LoginService{

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private PermissionMapper permissionMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private userRoleMapper userRoleMapper;
	
	@Resource
    private RolePermissionMapper rolePermissionMapper;
	
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

	@Override
	public Boolean addUser(User user) {
     
		String randomStr = Md5Util.getRandomStr(3);
	    
		String md5 = Md5Util.md5(user.getPassword(), randomStr);
		
		user.setPassword(md5);
		user.setSalt(randomStr);
		user.setLocked("0");
		int insertSelective = userMapper.insertSelective(user);		
		
		return true;
	}

	@Override
	public User selectName(User user) {

		User select = userMapper.select(user.getUsercode());
		
		return select;
	}
	
	public User selectID(User user) {

		User select = userMapper.selectUser(user.getId());
		
		return select;
	}


	@Override
	public List<Role> selectRole() {

        List<Role> selectRole = roleMapper.selectRole();
		
		return selectRole;
	}

	@Override
	public Role selectR(String id) {

		Role selectByPrimaryKey = roleMapper.selectRolename(id);
		System.out.println("Service"+selectByPrimaryKey);
		
		return selectByPrimaryKey;
	}

	@Override
	public boolean insertRole(userRole user) {
        
		int insertSelective = userRoleMapper.insertSelective(user);

		if(insertSelective==1)
		{
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public User selectUser(String id) {

		User selectUser = userMapper.selectUser(id);
		
		return selectUser;
	}

	@Override
	public boolean testUser(String pass3) {

		Subject subject = SecurityUtils.getSubject();
		User user2 = (User) subject.getPrincipal();
		
		User user = new User();
        user.setId(user2.getId());
        
        String randomStr = Md5Util.getRandomStr(3);
		String md5 = Md5Util.md5(pass3, randomStr);
        
		user.setSalt(randomStr);
		user.setPassword(md5);
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(user);
		
		if(updateByPrimaryKeySelective==1)
		{
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public List<User> selectUser() {
        
        List<User> allUser = userMapper.AllUser();
		
		return allUser;
	}

	@Override
	public boolean StopUser(User user) {

		int stopUser = userMapper.updateByPrimaryKey(user);
		
		if(stopUser==1)
		{
			return true;
		}
		else {
			return false;
		}
		
	
	}

	@Override
	public boolean deleteUser(User user) {

		int stopUser = userMapper.updateByPrimaryKey(user);
		
		if(stopUser==1)
		{
			return true;
		}
		else {
			return false;
		}
		
		
		
	}

	@Override
	public List<User> selectDUser() {
         
		List<User> allUser = userMapper.AllDeleteUser();
			
	     return allUser;
	}

	
	



}
