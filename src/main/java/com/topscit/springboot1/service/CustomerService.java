package com.topscit.springboot1.service;

import com.topscit.springboot1.bean.Customer;

public interface CustomerService {
	
	Customer selectByPrimaryKey(String cid);
	
	void updateByPrimaryKey(Customer record);

}
