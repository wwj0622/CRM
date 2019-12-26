package com.topscit.springboot1.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.util.ValidateCode;

@Controller
public class TestControllers {

	/**
     * 跳转页面
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("/loggin")
    public String login() {
        return "loginTest";
    }
     
    /**
     * 获取验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
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
     
    @RequestMapping("/submit")
    @ResponseBody
    public  HashMap<String, Object> submit(HttpSession session,String username,String password,String judge) 
    {
    	System.out.println("进入");
    	HashMap<String, Object> data = new HashMap<String,Object>();
        if(username==null||"".equals(username)) {
            return (HashMap<String, Object>) data.put("yhm", "请输入用户名");
        }
        if(password==null||"".equals(password)) {
            return (HashMap<String, Object>) data.put("mm", "请输入密码");
        }
        if(judge==null||"".equals(judge)) {
        	 return (HashMap<String, Object>) data.put("yzm", "请输入验证码");
        }
        Object code=session.getAttribute("code");
        if(code==null) {
        	 return (HashMap<String, Object>) data.put("erro", "验证码错误,请刷新验证码");
           
        }else {
            if(!judge.equals(code.toString())) {
                session.removeAttribute("code");
                
                return (HashMap<String, Object>) data.put("s","验证码错误");             
            }
        }
              session.removeAttribute("code");
        return  (HashMap<String, Object>) data.put("String", "登录成功");
    }

	
	
}
