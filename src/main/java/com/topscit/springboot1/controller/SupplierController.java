package com.topscit.springboot1.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResultBean getAllSupplier()
	{
		List<Supplier> allSupplier = supplierService.getAllSupplier();
		ResultBean resultBean = new ResultBean(ResultBean.STATA_SUCCESS, "查询成功", allSupplier);
		
		return resultBean;
	}
	
	
}
