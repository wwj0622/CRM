package com.topscit.springboot1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.ResultBean;
import com.topscit.springboot1.bean.Supplier;
import com.topscit.springboot1.service.SupplierService;


@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Resource
	private SupplierService supplierService;
	
	@RequestMapping("/getAllSupplier")
	@ResponseBody
	public ResultBean getAllSupplier(
			@RequestParam(value = "name",required = false)String name,
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data)
	{
		
		PageInfo<Supplier> supplierBy = supplierService.getSupplierBy(name, pn, size);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", supplierBy);
		return resultBean;
	}
	//分页查询所有,同步
//	@RequestMapping("/selectSupplier")
//	public String selectSupplier(
//			@RequestParam(defaultValue="1")int pn,
//			@RequestParam(defaultValue="5")int size,
//			Map<String, Object> data)
//	{
//		PageInfo<Supplier> selectSupplierList = supplierService.selectSupplierList(pn, size);
//		data.put("pageInfo", selectSupplierList);
//		return "forward:/mao/product-brand.jsp";
//	}
	
	//分页模糊查询
	@RequestMapping("/getSupplierByName")
	public ResultBean getSupplierByName(
			String sname,
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data)
	{
		PageInfo<Supplier> supplierBy = supplierService.getSupplierBy(sname, pn, size);
		
		data.put("pageInfo", supplierBy);
		System.out.println(supplierBy);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", data);
		return resultBean;
	}
	
	//添加Supplier
	@RequestMapping("/addSupplier")
	@ResponseBody
	public ResultBean addSupplier(Supplier supplier){
		System.out.println(supplier);
		boolean addSupplier = supplierService.addSupplier(supplier);
		if(addSupplier)
		{
			return new ResultBean(ResultBean.STATA_SUCCESS, "添加成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "添加失败");
		}
	}
}
