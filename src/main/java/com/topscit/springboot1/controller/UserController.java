package com.topscit.springboot1.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.topscit.springboot1.service.LoginService;

@Controller
public class UserController {

	@Resource
	private LoginService loginService;
	
	
	
}
