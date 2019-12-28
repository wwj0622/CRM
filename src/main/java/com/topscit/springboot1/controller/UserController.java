package com.topscit.springboot1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
	   
	    ArrayList<Permission> yi = new  ArrayList<Permission>();
	    for (Permission permission : select) {
			 if(permission.getType().equals("menu"))
			 {
				 yi.add(permission);
			 }
		}
	    
	    
	    for (Permission y : yi) {
			ArrayList<Permission> er = new ArrayList<Permission>();
			for (Permission permission : select) {
				if(permission.getParentid() == y.getId())
				{
					er.add(permission);
				}
			}
			y.setMiss(er);
		}
	    
	    data.put("select", yi);
	    
	    
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
		user.setOperationtime(new Date());
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
		user.setOperationtime(new Date());
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
		user.setOperationtime(new Date());
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
	
	@RequestMapping("/edit")
	public String editUser(String id,Map<String, Object> data)
	{
		data.put("id", id);
		return "forward:/member-power.jsp";
	}
	
	@RequestMapping("/IdUser")
	@ResponseBody
	public HashMap<String, Object> SelectIdUser(@RequestBody HashMap<String, String> data)
	{	
		User idUser = loginService.IdUser(data.get("id"));
		Role roleName = loginService.RoleName(data.get("id"));
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("user", idUser);
		hashMap.put("roleName", roleName);		
		
		return hashMap;
	}
	
	@RequestMapping("/updatepower")
	@ResponseBody
	public HashMap<String, Object> UpdatePower(@RequestBody User user)
	{ 
		boolean deletePower = loginService.deletePower(user.getId());		
		Boolean addUser = loginService.updatePower(user);
	
		userRole userRole = new userRole();
		userRole.setSysRoleId(user.getRoleid());
		userRole.setSysUserId(user.getId());
		
		boolean insertRole = loginService.insertRole(userRole);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("insertRole", insertRole);
		hashMap.put("String", addUser);
		
		return  hashMap;
	}
	
	@RequestMapping("/changepassword")
	public String changepassword(String id,Map<String, Object> data)
	{
	   User selectUser = loginService.selectUser(id);
	   
	   
	   data.put("String", selectUser);
	   
		return "forward:/change-password.jsp";
	}
	
	@RequestMapping("/updateUserPassowrd")
	@ResponseBody
	public HashMap<String, Object> updateUserPassowrd(@RequestBody User user)
	{
		boolean updateUserPassword = loginService.updateUserPassword(user);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String",updateUserPassword);
		return hashMap;		
	}
	
	
	@RequestMapping("/selectAllUser")
	@ResponseBody
	public List<User> SelectAllUser(@RequestBody HashMap<String, String> data)
	{	
	      List<User> selectUser = loginService.SelectUser(data);
		  
	      return selectUser;
	}
	
	
	@RequestMapping("/selectDeleteAllUser")
	@ResponseBody
	public List<User> selectDeleteAllUser(@RequestBody HashMap<String, String> data)
	{	
	      List<User> selectUser = loginService.SelectDeleteUser(data);
		  
	      return selectUser;
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public HashMap<String, Object> DeleteUser(String id)
	{
		boolean deleteUser = loginService.DeleteUser(id);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("String", deleteUser);
		
		return hashMap;
	}
	
	@RequestMapping("/AllUserrole")
	@ResponseBody
	public List<User> SelectRole()
	{
	    List<User> selectUserandRole = loginService.selectUserandRole();
		return selectUserandRole;
	}
	
	@RequestMapping("/AllPermis")
	@ResponseBody
	public List<Permission> SelecTall()
	{
		List<Permission> selecPermission = loginService.selecPermission();
		
		return selecPermission;
	}
	
	@RequestMapping("/roleuser")
	public String SelectUser(String id,Map<String, Object> data)
	{
		data.put("String", id);
		return "forward:/admin-role-permission.jsp";
		
	}
	
	@RequestMapping("/urp")
	public String Userp(String id,Map<String, Object> data)
	{
		data.put("String", id);
		return "forward:/admin-power-user.jsp";
		
	}
	
	
	@RequestMapping("/selectUP")
	@ResponseBody
	public Role selectUserPermission(@RequestBody HashMap<String, String> data)
	{
		String id = data.get("id");
		Role role = loginService.SelectRolePermission(id);

		return role;
		
	}
	
	@RequestMapping("/selectRp")
	@ResponseBody
	public Role selectRolePermission(@RequestBody HashMap<String, String> data)
	{
		String id = data.get("id");
		Role role = loginService.SelectRP(id);
		return role;	
	}
	
	@RequestMapping("/selectRoleName")
	@ResponseBody
	public HashMap<String, Object> selectRoleName(@RequestBody HashMap<String, String> data)
	{
	     	String name = data.get("name");
		    boolean selectRoleName = loginService.SelectRoleName(name);
		    HashMap<String, Object> hashMap = new HashMap<String,Object>();
		    hashMap.put("state", selectRoleName);
		    return hashMap;
	}
	
	
	@RequestMapping("/inserRaP")
	@ResponseBody
	public HashMap<String, Object> insertRolePermission(@RequestBody Role role)
	{
	   boolean insertRoleandPermission = loginService.InsertRoleandPermission(role);
       HashMap<String, Object> hashMap = new HashMap<String,Object>();
	   hashMap.put("state", insertRoleandPermission);
	   return hashMap;
		
	}
	
	@RequestMapping("/Allrole")
	@ResponseBody
	public List<Role> allRole()
	{
	    List<Role> selectRole = loginService.selectRole();
		return selectRole;
	}
	
	@RequestMapping("/updateRolePermission")
	@ResponseBody
	public HashMap<String, Object> updateRolePermission(@RequestBody Role role)
	{
		 boolean updateRolePermission = loginService.updateRolePermission(role);
		 HashMap<String, Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("state", updateRolePermission);
		 return hashMap;
	}
	
	@RequestMapping("/deleteRole")
	@ResponseBody
	public HashMap<String, Object> deleteRole(String id)
	{    
		 boolean updateRoleState = loginService.updateRoleState(id);
		 HashMap<String, Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("state",updateRoleState);
		 return hashMap;
		 
	}
	
	
}
