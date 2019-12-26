package com.topscit.springboot1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.bean.Storage;
import com.topscit.springboot1.bean.partsGoods;
import com.topscit.springboot1.dao.partsGoodsMapper;
import com.topscit.springboot1.service.StockService;
import com.topscit.springboot1.util.uploadFile;

@Controller
@RequestMapping("/stock")
public class StockController {
         
	@Resource
	private StockService stockService;
	
	
	@RequestMapping("/selectGoods")
	@ResponseBody
	public PageInfo<Goods> SelectGoods(
			@RequestParam(defaultValue="1") int pn,
			@RequestParam(defaultValue="3") int size			
			)
	{
		  PageInfo<Goods> selectGoods = stockService.SelectGoods(pn, size);
		
		  return selectGoods;
	}
	
	@RequestMapping("/getId")
	public String getId(String id,Map<String, Object> data)
	{
		data.put("id", id);
		return "forward:/parts_list.jsp";
	}
	
	@RequestMapping("/goodId")
	@ResponseBody
	public Goods  Goods(String id)
	{
		Goods selectGoods = stockService.selectGoods(id);
		return selectGoods;	
	}
	
	
	@RequestMapping("/partID")
	@ResponseBody
	public PageInfo<Parts>  Goods(String id,
			@RequestParam(defaultValue="1") int pn,
			@RequestParam(defaultValue="3") int size		
			)
	{
		PageInfo<Parts> selectGoodsID = stockService.selectGoodsID(id,pn,size);
		return selectGoodsID;	
	}
	
	
	@RequestMapping("/selectStorage")
	@ResponseBody
	public List<Storage> selectStorage()
	{
		List<Storage> selectStorage = stockService.selectStorage();
		
		return selectStorage;
	}
	
	@RequestMapping("/selectParts")
	@ResponseBody
	public List<Parts> selectParts()
	{
		List<Parts> selectParts = stockService.selectParts();
		
		return selectParts;
		
	}
	
    @RequestMapping(value = "/inserGoods",method =RequestMethod.POST )
	@ResponseBody
	public HashMap<String, Object>  InsertGoods(Goods goods,@RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest req)
	{    
		String upload = uploadFile.upload(file, req);
		goods.setGlogo(upload);		
		boolean insertGoods = stockService.insertGoods(goods);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", insertGoods);
		return hashMap;
	}
        

	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String, Object> DeleteGoods(String id)
	{
		boolean deleteGoods = stockService.deleteGoods(id);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", deleteGoods);
		
		return hashMap;	
	}
	
	@RequestMapping("/getID")
	public String getEditID(String id,Map<String, Object> data)
	{
		data.put("id", id);		
		return "forward:/product-edit.jsp";
	}
	
	
	@RequestMapping("/edit")
	@ResponseBody
	public Goods EditGoods(@RequestBody HashMap<String, String> data)
	{
		String id = data.get("id");
	    Goods eidtGoodsID = stockService.EidtGoodsID(id);
	   
	    return eidtGoodsID;
	}
	
	
	@RequestMapping("/updateGoods")
	@ResponseBody
	public HashMap<String, Object> UpdateGoods(Goods goods,MultipartFile file,HttpServletRequest req)
	{
		  String upload = uploadFile.upload(file, req);
		  goods.setGlogo(upload);
		  boolean goodsUpdate = stockService.GoodsUpdate(goods);
		  HashMap<String, Object> hashMap = new HashMap<String,Object>();
		  hashMap.put("state", goodsUpdate);
		  
		  return hashMap;
	}
	
	@RequestMapping("/partadd")
	public String PartaddJSP(String id,Map<String, Object> data)
	{
		data.put("id", id);	
		return "forward:/parts_add.jsp";
	}
	
	
	@RequestMapping("/inserParts")
	@ResponseBody
	public HashMap<String, Object> insertParts(Parts parts,MultipartFile file,HttpServletRequest req)
	{
		 System.out.println("进入");
		 String upload = uploadFile.upload(file, req);
		 parts.setPlogo(upload);
		 boolean insertParts = stockService.insertParts(parts);
		 HashMap<String, Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("state", insertParts);
		 return hashMap;		 
	}
	
	@RequestMapping("/deletePart")
	@ResponseBody
	public HashMap<String, Object> DeletePart(partsGoods partsGoods)
	{
		 boolean updateParts = stockService.UpdateParts(partsGoods);
		 
		 HashMap<String, Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("state", updateParts);
		 System.out.println(updateParts);
		 return hashMap;
		 
	}
	
	
	@RequestMapping("/AllPart")
	@ResponseBody
	public PageInfo<Parts> SelectStock(
			@RequestParam(defaultValue="1") int pn,
			@RequestParam(defaultValue="3")int size)
	{
	   PageInfo<Parts> pagePartsALL = stockService.PagePartsALL(pn, size);	
	   return pagePartsALL;
	}
	
	@RequestMapping("/deleteParts")
	@ResponseBody
	public HashMap<String, Object> DelectParts(String id)
	{
		boolean deleteParts = stockService.DeleteParts(id);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", deleteParts);
		
		return hashMap;
	}
	
	@RequestMapping("/addParts")
	@ResponseBody
	public HashMap<String, Object>  InsertParts(Parts parts,MultipartFile file,HttpServletRequest req)
	{
		String upload = uploadFile.upload(file, req);
		parts.setPlogo(upload);
		
		boolean addParts = stockService.addParts(parts);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();  
	    hashMap.put("state", addParts);
	    
	    return hashMap;
		
	}
    

	
	
}
