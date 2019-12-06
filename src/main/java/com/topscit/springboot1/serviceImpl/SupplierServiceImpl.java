package com.topscit.springboot1.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.topscit.springboot1.bean.Supplier;
import com.topscit.springboot1.dao.SupplierMapper;
import com.topscit.springboot1.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{

	@Resource
	private SupplierMapper supplierMapper;
	@Override
	public boolean addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		
		List<Supplier> allSupplier = supplierMapper.getAllSupplier();
		return allSupplier;
	}

	@Override
	public List<Supplier> findAllUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSupplier(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
