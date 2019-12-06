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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.bean.User;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	@ResponseBody
	public String Login(@RequestBody User user,HttpServletRequest req,Map<String, Object> data)
	{
		
	    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsercode(),user.getPassword());
		
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
			System.out.println("进入");
			return "forward:/permiss";
		}
		else
		{
			System.out.println("aaaa");
			return "redirect:/Login.jsp";
		}
		
		
		
	}

}
