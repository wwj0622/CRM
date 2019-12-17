package com.topscit.springboot1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.dao.OrderGoodsMapper;
import com.topscit.springboot1.dao.OrderMapper;
import com.topscit.springboot1.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public PageInfo<OrderGoods> selectListOrderGoodsByPn(String uid,int pn, int size) {
		PageHelper.startPage(pn, size);
		List<OrderGoods> list = orderGoodsMapper.selectListGoodsOrder(uid);
		PageInfo<OrderGoods> pageInfo = new PageInfo<OrderGoods>(list);
		return pageInfo;
	}

}
