package com.topscit.springboot1.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Supplier;

public interface SupplierService {

	//插入Supplier
	public boolean addSupplier(Supplier supplier);
	
	public List<Supplier> getAllSupplier();
	
	public boolean updateSupplier(Supplier supplier);
	
	public boolean deleteSupplier(Integer id);
	
	public PageInfo<Supplier> selectSupplierList(int pn,int size);
	
	//模糊查询
	public PageInfo<Supplier> getSupplierBy(String name,int pn,int size);
}
