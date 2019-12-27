package com.topscit.springboot1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.Salesman;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/salesman")
	public String customer(){
		return "forward:/ws/salesman.jsp";
	}
	
	@RequestMapping("/allcustomer")
	public String allCustomer(){
		return "forward:/ws/customer-list.jsp";
	}
	
	@RequestMapping("/allCustomerBySmid")
	@ResponseBody
	public List<Customer> allCustomerBySmid(){
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		List<Customer> selectAllKehuById = new ArrayList<Customer>();
		if(user.getId()!=null)
		{
			selectAllKehuById = customerService.selectAllKehuById(user.getId());
		}else
		{
			selectAllKehuById = customerService.selectAllKehuById("1");
		}
		
		return selectAllKehuById;
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
	
	@RequestMapping(value = "/updateBySaleman",method =RequestMethod.POST )
	@ResponseBody
	public Boolean updateBySaleman(@RequestBody Salesman salesman){
		salesman.setWork(null);
		salesman.setDept(null);
		customerService.updateByPrimaryKey(salesman);
		return true;
	}

	@RequestMapping(value = "/addCustomer")
	@ResponseBody
	public Boolean addCustomer(Customer customer){
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		if(user.getId()!=null)
		{
			customer.setSmid(user.getId());
		}else
		{
			customer.setSmid("1");
		}
		customer.setCproterty("0");
		customer.setCstate("0");
		customer.setCid(UUID.randomUUID().toString().replace("-", "").substring(0,30));
		customerService.insert(customer);
		return true;
	}
	
	@RequestMapping(value = "/delCustomer")
	@ResponseBody
	public Boolean delCustomer(String cid){
		System.out.println(cid);
		customerService.deleteByPrimaryKey(cid);
		return true;
	}
	
	@RequestMapping(value = "/selectSalesmanByNowId")
	@ResponseBody
	public Salesman selectSalesmanByNowId(){
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		Salesman salesman = new Salesman();
		if(user.getId()!=null)
		{
			salesman = customerService.selectSalesmanByPrimaryKey(user.getId());
		}else
		{
			salesman = customerService.selectSalesmanByPrimaryKey("1");
		}
		System.out.println(salesman);
		return salesman;
	}
}
