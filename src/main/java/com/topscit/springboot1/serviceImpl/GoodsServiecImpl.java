package com.topscit.springboot1.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Customer;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.OrderGoods;
import com.topscit.springboot1.bean.Storage;
import com.topscit.springboot1.dao.CustomerMapper;
import com.topscit.springboot1.dao.GoodsMapper;
import com.topscit.springboot1.dao.OrderGoodsMapper;
import com.topscit.springboot1.dao.StorageMapper;
import com.topscit.springboot1.service.GoodsService;

@Service("goodsService")
public class GoodsServiecImpl implements GoodsService{

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private StorageMapper storageMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public PageInfo<Goods> selectListGoodsByPn(int pn, int size) {
		PageHelper.startPage(pn, size);
		List<Goods> list = goodsMapper.selectListGoods();
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
		return pageInfo;
	}

	@Override
	public void delGoodsById(String[] gids) {
		for (int i = 0; i < gids.length; i++) {
			goodsMapper.deleteByPrimaryKey(gids[i].toString());
		}
		
	}

	@Override
	public PageInfo<Goods> selectListGoodsByLimit(String gname, int pn, int size) {
		PageHelper.startPage(pn, size);
		List<Goods> list = goodsMapper.selectListGoodsLimit(gname);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
		return pageInfo;
	}

	@Override
	public int updateByPrimaryKey(Goods goods) {
		int updateByPrimaryKey = goodsMapper.updateByPrimaryKey(goods);
		return updateByPrimaryKey;
	}

	@Override
	public int insertSelective(Goods goods) {
		int insertSelective = goodsMapper.insertSelective(goods);
		return insertSelective;
	}

	@Override
	public List<String> selectTid() {
		List<String> list = new ArrayList<String>();
		List<Storage> selectTid = storageMapper.selectTid();
		for (int i = 0; i < selectTid.size(); i++) {
			list.add(selectTid.get(i).getStid());
		}
		return list;
	}

	@Override
	public int insert(OrderGoods record) {
		int insert = orderGoodsMapper.insert(record);
		return insert;
	}

	@Override
	public int selectAllOrderById(String id) {
		int orderById = orderGoodsMapper.selectAllOrderById(id);
		return orderById;
	}

	@Override
	public List<OrderGoods> selectByGid(String uid) {
		List<OrderGoods> selectByGid = orderGoodsMapper.selectByGid(uid);
		return selectByGid;
	}

	@Override
	public int updateGtOgcount(String gid, String count) {
		int updateGtOgcount = orderGoodsMapper.updateGtOgcount(gid, count);
		return 0;
	}

	@Override
	public List<Customer> selectAllKehuById(String smid) {
		List<Customer> selectAllKehuById = customerMapper.selectAllKehuById(smid);
		return selectAllKehuById;
	}

}
