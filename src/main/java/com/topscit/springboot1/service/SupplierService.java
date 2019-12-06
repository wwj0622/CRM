package com.topscit.springboot1.service;

import java.util.List;

import com.topscit.springboot1.bean.Supplier;

public interface SupplierService {

	public boolean addSupplier(Supplier supplier);
	
	public List<Supplier> getAllSupplier();
	
	public List<Supplier> findAllUserByName(String name);
	
	public boolean updateSupplier(Supplier supplier);
	
	public boolean deleteSupplier(Integer id);
}
