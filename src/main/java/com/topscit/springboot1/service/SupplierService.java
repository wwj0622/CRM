package com.topscit.springboot1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Supplier;

public interface SupplierService {

	//插入Supplier
	public boolean addSupplier(Supplier supplier);
	
	public List<Supplier> getAllSupplier();
	
	//修改Suppllier
	public boolean updateSupplier(Supplier supplier);
	
	public boolean deleteSupplier(String id);
	
	public PageInfo<Supplier> selectSupplierList(int pn,int size);
	
	//根据id查询
	public Supplier getSupplierById(String id);
	
	//模糊查询
	public List<Supplier> getSupplierBy(String name);
}
