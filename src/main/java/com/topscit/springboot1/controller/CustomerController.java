package com.topscit.springboot1.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customer")
	public String customer(){
		return "forward:/ws/customer.jsp";
	}
	
	@RequestMapping("/customerMessage")
	@ResponseBody
	public Customer customerMessage(){
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		Customer customer = new Customer();
		if(user.getId()!=null)
		{
			customer = customerService.selectByPrimaryKey(user.getId());
		}else
		{
			customer = customerService.selectByPrimaryKey("1");
		}
		
		return customer;
	}
	
	
	@RequestMapping(value = "/updatecustomer",method =RequestMethod.POST )
	@ResponseBody
	public Boolean updatecustomer(Customer customers){
		customerService.updateByPrimaryKey(customers);
		return true;
	}
	
	@RequestMapping(value = "/selectAllCustomer")
	@ResponseBody
	public List<Customer> selectAllCustomer(){
		List<Customer> selectAllCustomer = customerService.selectAllCustomer();
		return selectAllCustomer;
	}

}
