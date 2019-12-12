package com.topscit.springboot1.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Supplier;
import com.topscit.springboot1.dao.SupplierMapper;
import com.topscit.springboot1.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{

	@Resource
	private SupplierMapper supplierMapper;
	
	@Resource
	private SqlSessionTemplate st;
	
	@Override
	public boolean addSupplier(Supplier supplier) {
		
		supplier.setSid(System.currentTimeMillis()+"user");
		Date date = new Date();
		supplier.setScreateTime(date);
		supplier.setSupdateTime(date);
		String state = "1";
		supplier.setSstate(state);
		
		int insertSelective = supplierMapper.insertSelective(supplier);
		if(insertSelective == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<Supplier> getAllSupplier() {
		
		List<Supplier> allSupplier = supplierMapper.getAllSupplier();
		return allSupplier;
	}

//	@Override
//	public List<Supplier> findAllUserByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	//修改Supllier
	@Override
	public boolean updateSupplier(Supplier supplier) {
		int selective = supplierMapper.updateByPrimaryKeySelective(supplier);
		if(selective == 1)
		{
			return true;
		}
		else{
			return false;
		}	}

	@Override
	public boolean deleteSupplier(String id) {
		int deleteByPrimaryKey = supplierMapper.deleteByPrimaryKey(id);
		if(deleteByPrimaryKey == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public PageInfo<Supplier> selectSupplierList(int pn, int size) {
		
		SupplierMapper mapper = st.getMapper(SupplierMapper.class);
		PageHelper.startPage(pn,size);
		List<Supplier> allSupplier = mapper.getAllSupplier();
		PageInfo<Supplier> pageInfo = new PageInfo<Supplier>(allSupplier);
		return pageInfo;
		
	}

	@Override
	public List<Supplier> getSupplierBy(String sName) {
		List<Supplier> supplierBy = supplierMapper.getSupplierBy(sName);
		return supplierBy;
	}

	@Override
	public Supplier getSupplierById(String id) {
		Supplier selectByPrimaryKey = supplierMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

}
