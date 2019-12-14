package com.topscit.springboot1.controller;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.service.GoodsService;
import com.topscit.springboot1.util.uploadFile;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/goodslist")
	public String selectListGoodsByPn(/*Map<String, Object> map,@RequestParam(defaultValue = "1")int pn, @RequestParam(defaultValue = "1")int size*/){
//		PageInfo<Goods> selectListGoodsByPn = goodsService.selectListGoodsByPn(pn, size);
//		map.put("pageinfo", selectListGoodsByPn);
//		System.out.println(selectListGoodsByPn);System.out.println();
		return "forward:/ws/member-list.jsp";
	}
	
	@RequestMapping("/goodslist1")
	@ResponseBody
	public PageInfo<Goods> selectListGoodsByPn1(@RequestParam(defaultValue = "1")int pn, @RequestParam(defaultValue = "8")int size,String content){
//		System.out.println(pn);
		if(content == null){
			PageInfo<Goods> selectListGoodsByPn = goodsService.selectListGoodsByPn(pn, size);
			return selectListGoodsByPn;
		}else{
			System.out.println(content);
			PageInfo<Goods> selectListGoodsByLimit = goodsService.selectListGoodsByLimit(content, pn, size);
			System.out.println(selectListGoodsByLimit);
			return selectListGoodsByLimit;
		}
		
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Boolean delGoods( String[] gidlist,int pn){
		goodsService.delGoodsById(gidlist);
//		System.out.println(pn);
		return true;
	}
	

	@RequestMapping(value = "/update",method =RequestMethod.POST )
	@ResponseBody
	public Boolean updateGoods(String gid,@RequestParam(value = "file",required = false) MultipartFile file,Goods goodsinfo,HttpServletRequest req){
		String show = null;
		String realPath = null;
		if(file != null)
		{
			realPath = req.getServletContext().getRealPath("/upload");
			File dir = new File(realPath);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			
			String fileName = System.currentTimeMillis()+file.getOriginalFilename();
			
			File dest = new File(realPath+"/"+fileName);
			try {
				file.transferTo(dest);
				show = "upload/"+fileName;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			
			goodsinfo.setGlogo(show);
			int updateByPrimaryKey = goodsService.updateByPrimaryKey(goodsinfo);
			if(updateByPrimaryKey>0){
				return true;
			}else{
				
				return false;
			}
		}else{
			int updateByPrimaryKey = goodsService.updateByPrimaryKey(goodsinfo);
			if(updateByPrimaryKey>0){
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	@RequestMapping(value = "/addgoods",method =RequestMethod.POST )
	@ResponseBody
	public Boolean addGoods(@RequestParam(value = "file",required = false) MultipartFile file,Goods goodsinfo,HttpServletRequest req){
		String upload = uploadFile.upload(file, req);
		goodsinfo.setGid(UUID.randomUUID().toString().replace("-", "").substring(0,30));
		System.out.println(goodsinfo);
		if(upload!=null){
			goodsinfo.setGlogo(upload);
			goodsService.insertSelective(goodsinfo);
		}else{
			goodsService.insertSelective(goodsinfo);
		}
		return true;
	}
	
	@RequestMapping("/selecttid")
	@ResponseBody
	public List<String> selecttid(){
		List<String> selectTid = goodsService.selectTid();
		return selectTid;
	}
}
