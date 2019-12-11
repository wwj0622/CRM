package com.topscit.springboot1.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.ResultBean;
import com.topscit.springboot1.bean.Supplier;
import com.topscit.springboot1.service.SupplierService;
import com.topscit.springboot1.util.uploadFile;


@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Resource
	private SupplierService supplierService;
	
	@RequestMapping("/getAllSupplier")
	@ResponseBody
	public ResultBean getAllSupplier(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="5")int size,
			Map<String, Object> data)
	{
		
		PageInfo<Supplier> supplierBy = supplierService.selectSupplierList(pn, size);
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
	
	//模糊查询
	@RequestMapping("/getSupplierByName")
	@ResponseBody
	public ResultBean getSupplierByName(String name)
	{
		System.out.println(name);
		List<Supplier> supplierBy = supplierService.getSupplierBy(name);
		System.out.println(supplierBy);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", supplierBy);
		return resultBean;
	}
	
	//添加Supplier
	@RequestMapping("/addSupplier")
	public String addSupplier(Supplier supplier,MultipartFile fm,HttpServletRequest req){
		
		String path = uploadFile.upload(fm, req);
		supplier.setSlogo(path);
		boolean addSupplier = supplierService.addSupplier(supplier);
		return "redirect:/mao/product-brand.jsp";
	}
	
	//删除供货商
	@RequestMapping("/delSupplier")
	@ResponseBody
	public ResultBean delSupplier(String[] sids){
		boolean deleteSupplier = false;
		for(int i=0;i < sids.length;i++){
			deleteSupplier = supplierService.deleteSupplier(sids[i]);
		}
		if(deleteSupplier)
		{
			return new ResultBean(ResultBean.STATA_SUCCESS, "删除成功");
		}
		else{
			return new ResultBean(ResultBean.STATA_FIAIL, "删除失败");
		}
	}
	
	//根据id查询
	@RequestMapping("/getSupplierById")
	@ResponseBody
	public ResultBean getSupplierById(String sid){
		Supplier supplierById = supplierService.getSupplierById(sid);
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", supplierById);
		return resultBean;
	}
	
	//修改Supplier
	@RequestMapping("/updateSupplier")
	public String updateSupplier(Supplier supplier,MultipartFile fm,HttpServletRequest req){
		
		String originalFilename = fm.getOriginalFilename();
		if(!originalFilename.equals("")){
			String path = uploadFile.upload(fm, req);
			supplier.setSlogo(path);
		}
		boolean updateSupplier = supplierService.updateSupplier(supplier);
		return "redirect:/mao/product-brand.jsp";
	}
}
