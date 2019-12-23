package com.topscit.springboot1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.dao.CustomerMapper;
import com.topscit.springboot1.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer selectByPrimaryKey(String cid) {
		Customer customer = customerMapper.selectByPrimaryKey(cid);
		return customer;
	}

	@Override
	public void updateByPrimaryKey(Customer record) {
		customerMapper.updateByPrimaryKey(record);
		
	}

	@Override
	public List<Customer> selectAllCustomer() {
		List<Customer> selectAllCustomer = customerMapper.selectAllCustomer();
		return selectAllCustomer;
	}

}
