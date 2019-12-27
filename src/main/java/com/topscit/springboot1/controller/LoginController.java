package com.topscit.springboot1.controller;

import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.topscit.springboot1.util.ValidateCode;

@Controller
public class LoginController {
	
	@RequestMapping("/user/check")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 通知浏览器不要缓存  
        response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "-1");  
        ValidateCode vCode = new ValidateCode(160,40,5,150);
        // 将验证码输入到session中，用来验证  
        String code=vCode.getCode();
        request.getSession().setAttribute("code", code);  
        // 输出到web页面  
        ImageIO.write(vCode.getBuffImg(), "jpg", response.getOutputStream()); 
    }
	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String Login(@RequestBody User user,HttpServletRequest req,Map<String, Object> data,HttpSession session2)
	{  	
		HttpSession session = req.getSession();
		Object code=session.getAttribute("code");

        if(code==null) 
        {
        	data.put("msg", "验证码错误,请刷新验证码");
        	return "forward:/Login.jsp";
        }else 
        {
            if(!user.getJudge().toString().toUpperCase().equals(code.toString())) 
            {   
                session.removeAttribute("code");
                data.put("msg","验证码错误"); 
                return "forward:/Login.jsp";
            }
        }     
        session.removeAttribute("code");
  				
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
			return "forward:/permiss";
		}
		else
		{
			System.out.println("aaaa");
			return "redirect:/Login.jsp";
		}
		
		
		
	}

}
