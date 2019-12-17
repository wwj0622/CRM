package com.topscit.springboot1.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
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
	public String selectAllOrderById()
	{
		return "forward:/ws/goods-order.jsp";
	}
	
	@RequestMapping("/allorderbyid")
	@ResponseBody
	public PageInfo<OrderGoods> selectListOrderGoodsById(@RequestParam(defaultValue = "1")int pn, @RequestParam(defaultValue = "8")int size,String content){
//			User user = (User)SecurityUtils.getSubject().getPrincipal();
			User user = new User();
			PageInfo<OrderGoods> selectListOrderGoodsByPn = new PageInfo<OrderGoods>();
			if(user.getId()!=null)
			{
				selectListOrderGoodsByPn = orderService.selectListOrderGoodsByPn(user.getId(),pn, size);
			}else
			{
				selectListOrderGoodsByPn = orderService.selectListOrderGoodsByPn("1",pn, size);
			}
			System.out.println(selectListOrderGoodsByPn.getList().get(1).toString());
			return selectListOrderGoodsByPn;
		
		
	}

}
