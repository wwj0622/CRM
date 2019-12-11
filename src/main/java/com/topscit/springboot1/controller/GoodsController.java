package com.topscit.springboot1.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.service.GoodsService;

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
	public PageInfo<Goods> selectListGoodsByPn1(@RequestParam(defaultValue = "1")int pn, @RequestParam(defaultValue = "8")int size){
//		System.out.println(pn);
		PageInfo<Goods> selectListGoodsByPn = goodsService.selectListGoodsByPn(pn, size);
		return selectListGoodsByPn;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Boolean delGoods( String[] gidlist,int pn){
		goodsService.delGoodsById(gidlist);
//		System.out.println(pn);
		return true;
	}
	
}
