package com.topscit.springboot1.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Order;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.dao.OrderGoodsMapper;
import com.topscit.springboot1.service.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/allorder")
	public String selectAllOrderById(String cid,Map<String,String> map)
	{
		map.put("cid", cid);
		return "forward:/ws/goods-order.jsp";
	}
	
	@RequestMapping("/allorderbyid")
	@ResponseBody
	public PageInfo<OrderGoods> selectListOrderGoodsById(@RequestParam(defaultValue = "1")int pn, @RequestParam(defaultValue = "8")int size,String content,String cid){
			PageInfo<OrderGoods> selectListOrderGoodsByPn = new PageInfo<OrderGoods>();
			if(cid==null)
			{
				selectListOrderGoodsByPn = orderService.selectListOrderGoodsByPn("1",pn, size);
			}else
			{
				selectListOrderGoodsByPn = orderService.selectListOrderGoodsByPn(cid,pn, size);
			}
			return selectListOrderGoodsByPn;
	}
	
	@RequestMapping("/updateordergoodsbycount")
	@ResponseBody
	public Boolean updateOrderGoodsByCount(String oid , String ogcount){
		orderService.updateOgcountByOid(oid, ogcount);
		return true;
	}
	
	@RequestMapping("/delordergoods")
	@ResponseBody
	public Boolean delOrderGoods(String oid){
		orderService.deleteByPrimaryKey(oid);
		return true;
	}
	
	@RequestMapping("/buyordergoods")
	@ResponseBody
	public Boolean buyOrderGoods(String[] list,String cid){
		System.out.println(list[0]);
		System.out.println(cid);
		orderService.buyOrderGoods(list,cid);
		return true;
		
	}
	
	@RequestMapping(value = "/addordergoods",method =RequestMethod.POST )
	@ResponseBody
	public Boolean addOrderGoods(@RequestBody OrderGoods[] orderGoods){
		System.out.println(orderGoods);
		System.out.println(orderGoods[0]);
//		JSONArray data = JSONArray.fromObject(orderGoods);
//		List<OrderGoods> list =(List<OrderGoods>)JSONArray.toCollection(data, OrderGoods.class);
		return true;
	}
}
