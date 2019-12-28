package com.topscit.springboot1.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.RolePermission;
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
	
		Date date = new Date();
		
		user.setPassword(md5);
		user.setSalt(randomStr);
		user.setLocked("0");
		user.setJointime(new Date());
		System.out.println();
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

		user.setOperationtime(new Date());
		int stopUser = userMapper.updateByPrimaryKeySelective(user);
		
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

		int stopUser = userMapper.updateByPrimaryKeySelective(user);
		
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

	@Override
	public User IdUser(String id) {
        
		User user = userMapper.selectByPrimaryKey(id);
		
		return user;
	}

	@Override
	public Role RoleName(String id) {

		Role roleName = roleMapper.RoleName(id);
		
		return roleName;
	}

	@Override
	public boolean deletePower(String id) {

		int deleteByPrimaryKey = userRoleMapper.deleteByPrimaryKey(id);
		
		if(deleteByPrimaryKey==1)
		{
			return true;
		}
		else
		{
		return false;
    	}
	}

	@Override
	public boolean updatePower(User user) {
        user.setId(user.getId());
        int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(user);	
        
    	if(updateByPrimaryKeySelective==1)
		{
			return true;
		}
		else
		{
		return false;
    	}
	}

	@Override
	public boolean updateUserPassword(User user) {
        
		String randomStr = Md5Util.getRandomStr(3);
		String md5 = Md5Util.md5(user.getPassword(), randomStr);
		
		User user2 = new User();
		user2.setId(user.getId());
		user2.setPassword(md5);
		user2.setSalt(randomStr);
		user2.setOperationtime(new Date());
		int updateByPrimaryKeySelective = userMapper.updateByPrimaryKeySelective(user2);
		
		if(updateByPrimaryKeySelective==1)
		{
			return true;
		}
		else
		{
		    return false;
    	}
	}

	@Override
	public List<User> SelectUser(HashMap<String, String> data) {
		String start = data.get("start");
		String end = data.get("end");
		
		String name = data.get("name");
		
		String name2="%"+name+"%";
		
		String pattern="yyyy-MM-dd";
		
		 Date parse = null;
		 Date parse2 = null;
		 List<User> dateUser = null;
		SimpleDateFormat simple = new SimpleDateFormat(pattern);
		try {
		    if(!start.equals(""))
		    {
			  parse = simple.parse(start);
		    }
		    if(!end.equals(""))
		    {
			  parse2 = simple.parse(end);
		    }
		 dateUser = userMapper.DateUser(parse, parse2, name2);
			
		} catch (ParseException e) {
			System.out.println("日期转换错误");
		}
		
		return dateUser;
	}

	@Override
	public List<User> SelectDeleteUser(HashMap<String, String> data) {
			
		    String start = data.get("start");
			String end = data.get("end");
			
			String name = data.get("name");
			
			String name2="%"+name+"%";
			
			String pattern="yyyy-MM-dd";
			
			 Date parse = null;
			 Date parse2 = null;
			 List<User> dateUser = null;
			SimpleDateFormat simple = new SimpleDateFormat(pattern);
			try {
			    if(!start.equals(""))
			    {
				  parse = simple.parse(start);
			    }
			    if(!end.equals(""))
			    {
				  parse2 = simple.parse(end);
			    }
			 dateUser = userMapper.DatedeleteUser(parse, parse2, name2);
				
			} catch (ParseException e) {
				System.out.println("日期转换错误");
			}
			
			return dateUser;
	}

	@Override
	public boolean DeleteUser(String id) {
         
		User user = new User();
		user.setId(id);
		user.setLocked("4");
		
		int updateByPrimaryKey = userMapper.updateByPrimaryKey(user);

		if(updateByPrimaryKey==1)
		{
			return true;
		}
		else
		{
		    return false;
    	}
	}

	@Override
	public List<Role> selectPermission(String id) {
        
        List<Role> selectPermission = permissionMapper.selectPermission(id);

		
		return selectPermission;
	}

	@Override
	public List<User> selectUserandRole() {

        List<User> selectUserRole = userMapper.selectUserRole();
		
		return selectUserRole;
	}

	@Override
	public List<Permission> selecPermission() {
		List<Permission> selectall = permissionMapper.selectall();
		
		return selectall;
	}

	@Override
	public Role SelectRolePermission(String id) {

		Role selectRP = roleMapper.SelectRP(id);
		
		return selectRP;
	}

	@Override
	public boolean SelectRoleName(String name) {
         
		Role selectRoleName = roleMapper.SelectRoleName(name);

		if(selectRoleName==null)
		{
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean InsertRoleandPermission(Role role) {
         
		String string = UUID.randomUUID().toString();
		role.setId(string);
		role.setState("0");
		
		int r = roleMapper.insertSelective(role);
		
		
		String[] perID = role.getPerID();
		RolePermission record = new RolePermission();
		int rp = 0;
		String string2 = UUID.randomUUID().toString();
		for (int i = 0; i < perID.length; i++) {
			record.setSysRoleId(string);
			record.setSysPermissionId(perID[i]);
			rp = rolePermissionMapper.insertSelective(record);
		}
		if(r==1&&rp==1){
			return true;
		}
		else
		{
			return false;
		}	
	}

	@Override
	public List<Role> RoleUser() {

        List<Role> roleUser = roleMapper.RoleUser();
		
		return roleUser;
	}
	
	
	public Role RoleUser(String id){
		Role selectPermission = roleMapper.SelectPermission(id);	
		return selectPermission;
	}

	@Override
	public Role SelectRP(String id) {
       
        Role selectPermission = roleMapper.SelectPermission(id);  
		
		return selectPermission;
	}

	@Override
	public boolean updateRolePermission(Role role) {
         
		int i = roleMapper.updateByPrimaryKeySelective(role);
		
		String id = role.getId();
		boolean d = rolePermissionMapper.DelectRolePermission(id);
		
		String[] perID = role.getPerID();
		RolePermission rp = new RolePermission();
		int insertSelective = 0;
		for (int j = 0; j < perID.length; j++) {
			rp.setSysRoleId(id);
			rp.setSysPermissionId(perID[j]);
		insertSelective = rolePermissionMapper.insertSelective(rp);
		}
		
			return true;
   }

	@Override
	public boolean updateRoleState(String id) {
        boolean updateRoleState = roleMapper.updateRoleState(id);
		 
		return updateRoleState;
	}

	
	
	

	
	



}
