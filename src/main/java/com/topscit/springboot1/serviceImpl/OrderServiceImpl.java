package com.topscit.springboot1.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.xpath.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Order;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.bean.Yorder;
import com.topscit.springboot1.dao.GoodsMapper;
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
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public PageInfo<OrderGoods> selectListOrderGoodsByPn(String uid,int pn, int size) {
		PageHelper.startPage(pn, size);
		List<OrderGoods> list = orderGoodsMapper.selectListGoodsOrder(uid);
		PageInfo<OrderGoods> pageInfo = new PageInfo<OrderGoods>(list);
		return pageInfo;
	}

	@Override
	public int deleteOrderByPrimaryKey(String yoid) {
		int deleteByPrimaryKey = orderMapper.deleteByPrimaryKey(yoid);
		return deleteByPrimaryKey;
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
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		User user = new User();
		String yoid = UUID.randomUUID().toString().replace("-", "").substring(0,30);
		int money = 0;
		for (int i = 0; i < list.length; i++) {
			OrderGoods orderGoods = orderGoodsMapper.selectOrderGoodsByPrimaryKey(list[i]);
			goodsMapper.updateGoodsByGid(orderGoods.getGid(), Integer.valueOf(orderGoods.getOgcount()));
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
		if(user.getId()!=null)
		{
			yorder.setYstate(user.getId());
		}else{
			yorder.setYstate("1");
		}
		
		yorder.setCid(cid);
		System.out.println(yorder);
		yorderMapper.insert(yorder);
	}

	@Override
	public void addOrderGoods(OrderGoods[] list) {
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String yoid = UUID.randomUUID().toString().replace("-", "").substring(0,30);
		int money = 0;
		try {
			for (int i = 0; i < list.length; i++) {
				Goods goods = goodsMapper.selectByPrimaryKey(list[i].getGid());
				String oid = UUID.randomUUID().toString().replace("-", "").substring(0,30);
				list[i].setOid(oid);
				list[i].setOgremark(yoid);
				orderGoodsMapper.insert(list[i]);
				money = money + Integer.valueOf(goods.getGprice())*Integer.valueOf(list[i].getOgcount());
			}
		} catch (Exception e) {
			System.out.println("错误信息:"+e);
		}
		
//		Order order = new Order(yoid,list[0].getUid(),null,String.valueOf(money),"0",null,user.getId());
		Order order = new Order(yoid,list[0].getUid(),null,String.valueOf(money),"0",null,"1");
		orderMapper.insert(order);
	}

	@Override
	public List<Order> selectAllOrderByKid() {
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		List<Order> selectAllOrderByKid = orderMapper.selectAllOrderByKid("1");
		return selectAllOrderByKid;
	}

	@Override
	public List<OrderGoods> selectOrderGoodsByYoid(String id) {
		List<OrderGoods> selectOrderGoodsByYoid = orderGoodsMapper.selectOrderGoodsByYoid(id);
		return selectOrderGoodsByYoid;
	}

	@Override
	public OrderGoods selectListGoodsOrderByOid(String oid) {
		OrderGoods orderGoods = orderGoodsMapper.selectListGoodsOrderByOid(oid);
		return orderGoods;
	}

	@Override
	public int updateOsumByOid(String oid, String yoid) {
		List<OrderGoods> orderGoodsByYoid = orderGoodsMapper.selectOrderGoodsByYoid(yoid);
		int money = 0;
		for (int i = 0; i < orderGoodsByYoid.size(); i++) {
			String count = orderGoodsByYoid.get(i).getOgcount();
			Goods goods = goodsMapper.selectByPrimaryKey(orderGoodsByYoid.get(i).getGid());
			String price = goods.getGprice();
			money = money + Integer.valueOf(count)*Integer.valueOf(price);
		}
		System.out.println(oid);
		System.out.println(money);
		orderMapper.updateOsumByOid(yoid, String.valueOf(money));
		return 0;
	}

	@Override
	public Order selectOrderByOid(String yoid) {
		Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(yoid);
		return selectByPrimaryKey;
	}

	@Override
	public int updateOstateOid(String oid) {
		int updateOstateOid = orderMapper.updateOstateOid(oid);
		return updateOstateOid;
	}

	@Override
	public int deleteOrderGoodsByYoid(String yoid) {
		int deleteOrderGoodsByYoid = orderGoodsMapper.deleteOrderGoodsByYoid(yoid);
		return deleteOrderGoodsByYoid;
	}

	@Override
	public int insert(Yorder record) {
		int insert = yorderMapper.insert(record);
		return insert;
	}

	@Override
	public List<Yorder> selectAllYorder() {
		List<Yorder> selectAllYorder = yorderMapper.selectAllYorder();
		return selectAllYorder;
	}

	@Override
	public List<OrderGoods> selectListGoodsOrderByYoid(String yoid) {
		List<OrderGoods> selectListGoodsOrderByYoid = orderGoodsMapper.selectListGoodsOrderByYoid(yoid);
		return selectListGoodsOrderByYoid;
	}

}
