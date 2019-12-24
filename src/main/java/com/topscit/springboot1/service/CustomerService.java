package com.topscit.springboot1.service;

import java.util.List;

import com.topscit.springboot1.bean.Customer;

public interface CustomerService {
	
	Customer selectByPrimaryKey(String cid);
	
	void updateByPrimaryKey(Customer record);
	
	List<Customer> selectAllCustomer();

}
