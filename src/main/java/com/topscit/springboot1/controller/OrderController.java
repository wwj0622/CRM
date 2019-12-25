package com.topscit.springboot1.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Order;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.bean.Yorder;
import com.topscit.springboot1.dao.OrderGoodsMapper;
import com.topscit.springboot1.service.CustomerService;
import com.topscit.springboot1.service.OrderService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/allorder")
	public String selectAllOrderById()
	{
		return "forward:/ws/goods-order.jsp";
	}
	
	@RequestMapping("/outbound")
	public String selectAllOutbound()
	{
		return "forward:/ws/outbound.jsp";
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
		orderService.addOrderGoods(orderGoods);
//		JSONArray data = JSONArray.fromObject(orderGoods);
//		List<OrderGoods> list =(List<OrderGoods>)JSONArray.toCollection(data, OrderGoods.class);
		return true;
	}
	
	@RequestMapping("/selectAllOrderByKid")
	@ResponseBody
	public List<Order> selectAllOrderByKid(){
		List<Order> selectAllOrderByKid = orderService.selectAllOrderByKid();
		return selectAllOrderByKid;
	}
	
	@RequestMapping("/ordergoods")
	public String ordergoods(String oid,Map<String, Object> map){
		map.put("oid",oid);
		return "forward:/ws/order-details.jsp";
	}
	
	@RequestMapping(value = "/orderGoodsByYoid",method =RequestMethod.POST )
	@ResponseBody
	public List<OrderGoods> selectOrderGoodsByYoid(String yoid){
		List<OrderGoods> OrderGoodsList = orderService.selectOrderGoodsByYoid(yoid);
		List<OrderGoods> arrayList = new ArrayList<OrderGoods>();
		
		for(int i = 0;i<OrderGoodsList.size();i++)
		{
			OrderGoods goodsOrderByOid = orderService.selectListGoodsOrderByOid(OrderGoodsList.get(i).getOid());
			arrayList.add(goodsOrderByOid);
		}
		System.out.println(arrayList);
		return arrayList;
	}
	
	@RequestMapping("/updateogcount")
	@ResponseBody
	public Boolean updateOgcount(String oid,String ogcount,String yoid){
		
		if(ogcount.equals("0"))
		{
			orderService.deleteByPrimaryKey(oid);
			List<OrderGoods> OrderGoodsList = orderService.selectOrderGoodsByYoid(yoid);
			if(OrderGoodsList.size()==0)
			{
				orderService.deleteOrderByPrimaryKey(yoid);
			}
		}else
		{
			int updateOgcountByOid = orderService.updateOgcountByOid(oid, ogcount);
			orderService.updateOsumByOid(oid, yoid);
		}
		return true;
	}
	
	@RequestMapping("/delOrderGoodsByOid")
	@ResponseBody
	public Boolean delOrderGoodsByOid(String oid,String yoid){
		orderService.deleteByPrimaryKey(oid);
		List<OrderGoods> OrderGoodsList = orderService.selectOrderGoodsByYoid(yoid);
		if(OrderGoodsList.size()==0)
		{
			orderService.deleteOrderByPrimaryKey(yoid);
		}
		orderService.updateOsumByOid(oid, yoid);
		return true;
	}
	
	@RequestMapping("/payOrder")
	@ResponseBody
	public Boolean payOrder(String yoid){
		System.out.println(yoid);
		Boolean istrue = true;
		Order order = orderService.selectOrderByOid(yoid);
		if(order.getOstate().equals("1"))
		{
			istrue = false;
		}else{
			int updateOstateOid = orderService.updateOstateOid(yoid);
			//商品出库单
			Yorder yorder = new Yorder();
			yorder.setCid(order.getCid());
			yorder.setYmoney(order.getOsum());
			yorder.setYstate("1");
			yorder.setYoid(order.getOid());
			
			int insert = orderService.insert(yorder);
		}
		return istrue;
	}
	
	
	@RequestMapping(value = "/delOrderAndOrderGoods",method =RequestMethod.POST )
	@ResponseBody
	public Boolean delOrderAndOrderGoods(String yoid){
		int deleteOrderByPrimaryKey = orderService.deleteOrderByPrimaryKey(yoid);
		int deleteOrderGoodsByYoid = orderService.deleteOrderGoodsByYoid(yoid);
		return true;
	}
	
	@RequestMapping("/selectAllYorder")
	@ResponseBody
	public List<Yorder> selectAllYorder(){
		List<Yorder> selectAllYorder = orderService.selectAllYorder();
		for (int i = 0; i < selectAllYorder.size(); i++) {
			List<OrderGoods> selectListGoodsOrderByYoid = orderService.selectListGoodsOrderByYoid(selectAllYorder.get(i).getYoid());
			for (int j = 0; j < selectListGoodsOrderByYoid.size(); j++) {
				Customer customer = customerService.selectByPrimaryKey(selectListGoodsOrderByYoid.get(j).getUid());
				selectListGoodsOrderByYoid.get(j).setCustomer(customer);
			}
			selectAllYorder.get(i).setOrderGoods(selectListGoodsOrderByYoid);
		}
		return selectAllYorder;
	}
}
