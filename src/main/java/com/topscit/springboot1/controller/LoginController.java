package com.topscit.springboot1.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String Login(String uname,String upass,HttpServletRequest req,Map<String, Object> data)
	{
	    UsernamePasswordToken token = new UsernamePasswordToken(uname,upass);
		
		Subject subject = SecurityUtils.getSubject();
	    
		int msg = 0;
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			msg = 1;
		} catch(IncorrectCredentialsException e )
		{
			msg = 2;
		} catch (AuthenticationException e)
		{
			msg = 3;
		}
		if(subject.isAuthenticated())
		{
			return "redirect:/index.jsp";
		}
		else
		{
			return "redirect:/Login.jsp";
		}
		
		
		
	}

}
