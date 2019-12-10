package com.topscit.springboot1.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.bean.userRole;
import com.topscit.springboot1.service.LoginService;

@Controller
public class UserController {

	@Resource
	private LoginService loginService;
	
	@RequestMapping("/permiss")
	public String SelectPermissiona(User user,Map<String, Object> data)
	{
		Subject subject = SecurityUtils.getSubject();
		User user2 = (User) subject.getPrincipal();
		User u = loginService.selectUser(user2.getId());
	    List<Permission> select = loginService.select(user2.getId());
	    data.put("select", select);
	    data.put("name", u.getUsername());
	    data.put("namecode", u.getUsercode());
		return "forward:/index.jsp";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public HashMap<String, Object> addUser(@RequestBody User user)
	{

		Role role = loginService.selectR(user.getRoleid());
	
		user.setUsername(role.getName());
		user.setJointime(new Date());
		Boolean addUser = loginService.addUser(user);
		userRole userRole = new userRole();
		userRole.setSysRoleId(user.getRoleid());
		userRole.setSysUserId(user.getId());
		loginService.insertRole(userRole);
		
		boolean state=true;
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", state);	
		return hashMap;
		
	}
	
	@RequestMapping("/updatepass")
	@ResponseBody
	public HashMap<String, Object> updatePass(@RequestBody User user)
	{
		Subject subject = SecurityUtils.getSubject();
		User user2 = (User) subject.getPrincipal();
		User u = loginService.selectUser(user2.getId());
		int state=0;
		if(!u.getIdcard().equals(user.getIdcard()))
		{
			state=1;
		}
		if(!u.getPhone().equals(user.getPhone()))
		{
			state=2;
		}
		
		if(state==0){
			boolean testUser = loginService.testUser(user.getPassword());
			
			state=3;
		}
		System.out.println(state);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", state);
		return hashMap;
		
	}

	
	@RequestMapping("/selectID")
	@ResponseBody
	public HashMap<String, Object>  selectID(@RequestBody User user)
	{  
	
		User addUser = loginService.selectID(user);
		
		boolean state;
		
		if(addUser==null)
		{
			state=false;
		}
		else {
			state=true;
		}
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", state);
		
		return hashMap;
	}
	
	@RequestMapping("/selectname")
	@ResponseBody
	public HashMap<String, Object>  User(@RequestBody User user)
	{  
	
		User addUser = loginService.selectName(user);
		
		boolean state;
		
		if(addUser==null)
		{
			state=false;
		}
		else {
			state=true;
		}
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", state);
		
		return hashMap;
	}
	
	@RequestMapping("/selectRole")
	@ResponseBody
	public List<Role> selectPermission()
	{
	  List<Role> selectRole = loginService.selectRole();
	
	  return  selectRole;
	}
	
	
	@RequestMapping("/selectUser")
	@ResponseBody
	public List<User> SelectUser()
	{
		List<User> selectUser = loginService.selectUser();
		
    	return  selectUser;
	}
	
	
	@RequestMapping("/sduser")
	@ResponseBody
	public List<User> SelectDeleteUser()
	{
		List<User> selectUser = loginService.selectDUser();
		
    	return  selectUser;
	}
	
   
	@RequestMapping("/stop")
	@ResponseBody
	public HashMap<String, Object> StopUser(String id)
	{
		User user = new User();
		user.setId(id);
		user.setLocked("1");
		
		boolean stopUser = loginService.StopUser(user);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", stopUser);
		
		return hashMap;
	}
	
	@RequestMapping("/use")
	@ResponseBody
	public HashMap<String, Object> useUser(String id)
	{
		User user = new User();
		user.setId(id);
		user.setLocked("0");
		
		boolean stopUser = loginService.StopUser(user);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", stopUser);
		
		return hashMap;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String, Object>  deleteUser(String id)
	{
		User user = new User();
		user.setId(id);
		user.setLocked("3");
		boolean deleteUser = loginService.deleteUser(user);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", deleteUser);
		return hashMap;
		
	}
	
	
	@RequestMapping("/cancel")
	@ResponseBody
	public HashMap<String, Object> cancelUser(@RequestBody HashMap<String, String> data)
	{
		User user = new User();
		user.setId(data.get("id"));
		user.setLocked("0");
	
		boolean stopUser = loginService.StopUser(user);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", stopUser);
		
		return hashMap;
		
	}
	
	
}
