package com.topscit.springboot1.service;

import java.util.List;

import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.Salesman;

public interface CustomerService {
	
	Customer selectByPrimaryKey(String cid);
	
	void updateByPrimaryKey(Customer record);
	
	List<Customer> selectAllCustomer();
	
	List<Customer> selectAllKehuById(String smid);
	
	int insert(Customer record);
	
	int deleteByPrimaryKey(String cid);
	
	Salesman selectSalesmanByPrimaryKey(String smid);
	
	int updateByPrimaryKey(Salesman record);
}
