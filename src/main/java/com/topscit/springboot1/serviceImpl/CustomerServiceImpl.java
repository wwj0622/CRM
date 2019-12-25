package com.topscit.springboot1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.Salesman;
import com.topscit.springboot1.dao.CustomerMapper;
import com.topscit.springboot1.dao.SalesmanMapper;
import com.topscit.springboot1.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SalesmanMapper salesmanMapper;
	
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

	@Override
	public List<Customer> selectAllKehuById(String smid) {
		List<Customer> selectAllKehuById = customerMapper.selectAllKehuById(smid);
		
		return selectAllKehuById;
	}

	@Override
	public int insert(Customer record) {
		int insert = customerMapper.insert(record);
		return insert;
	}

	@Override
	public int deleteByPrimaryKey(String cid) {
		int deleteByPrimaryKey = customerMapper.deleteByPrimaryKey(cid);
		return deleteByPrimaryKey;
	}

	@Override
	public Salesman selectSalesmanByPrimaryKey(String smid) {
		Salesman salesman = salesmanMapper.selectByPrimaryKey(smid);
		return salesman;
	}

	@Override
	public int updateByPrimaryKey(Salesman record) {
		int updateByPrimaryKey = salesmanMapper.updateByPrimaryKey(record);
		return updateByPrimaryKey;
	}

}
