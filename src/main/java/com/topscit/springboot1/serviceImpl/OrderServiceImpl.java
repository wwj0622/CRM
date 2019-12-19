package com.topscit.springboot1.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.xpath.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Order;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.Yorder;
import com.topscit.springboot1.dao.OrderGoodsMapper;
import com.topscit.springboot1.dao.OrderMapper;
import com.topscit.springboot1.dao.YorderMapper;
import com.topscit.springboot1.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private YorderMapper yorderMapper;

	@Override
	public PageInfo<OrderGoods> selectListOrderGoodsByPn(String uid,int pn, int size) {
		PageHelper.startPage(pn, size);
		List<OrderGoods> list = orderGoodsMapper.selectListGoodsOrder(uid);
		PageInfo<OrderGoods> pageInfo = new PageInfo<OrderGoods>(list);
		return pageInfo;
	}

	@Override
	public int deleteByPrimaryKey(String oid) {
		int deleteByPrimaryKey = orderGoodsMapper.deleteByPrimaryKey(oid);
		return deleteByPrimaryKey;
	}

	@Override
	public int updateOgcountByOid(String oid, String count) {
		orderGoodsMapper.updateOgcountByOid(oid, count);
		return 0;
	}

	@Override
	public void buyOrderGoods(String[] list,String cid) {
		String yoid = UUID.randomUUID().toString().replace("-", "").substring(0,30);
		int money = 0;
		for (int i = 0; i < list.length; i++) {
			OrderGoods orderGoods = orderGoodsMapper.selectOrderGoodsByPrimaryKey(list[i]);
			Order order = new Order();
			order.setCid(cid);
			order.setYoid(yoid);
			order.setOid(orderGoods.getOid());
			order.setOremark(orderGoods.getOgremark());
			order.setOstate("1");
			order.setOsum(String.valueOf(Integer.valueOf(orderGoods.getOgcount())*Integer.valueOf(orderGoods.getGoods().getGprice())));
			money = money + Integer.valueOf(orderGoods.getOgcount())*Integer.valueOf(orderGoods.getGoods().getGprice());
			orderMapper.insert(order);
			orderGoodsMapper.deleteByPrimaryKey(list[i]);
		}
		Yorder yorder = new Yorder();
		yorder.setYoid(yoid);
		yorder.setYmoney(String.valueOf(money));
		yorder.setYstate("1");
		yorder.setYoid(cid);
		System.out.println();
		yorderMapper.insert(yorder);
	}

}
