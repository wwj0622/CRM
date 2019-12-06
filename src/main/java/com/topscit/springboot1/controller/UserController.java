package com.topscit.springboot1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.service.LoginService;

@Controller
public class UserController {

	@Resource
	private LoginService loginService;
	
/*	@RequestMapping("/permission")
	@ResponseBody
	public List<Permission> SelectPermission(User user)
	{
		
		user.setId("a");
	    List<Permission> select = loginService.select(user.getId());
		
		return select;
	}
	*/
	@RequestMapping("/permiss")
	public String SelectPermissiona(User user,Map<String, Object> data)
	{
	
		
		Subject subject = SecurityUtils.getSubject();
		User user2 = (User) subject.getPrincipal();
		System.out.println(user2.getId());
	    List<Permission> select = loginService.select(user2.getId());
		
	    data.put("select", select);
	    
		return "forward:/index.jsp";
	}
	
	
	
}
