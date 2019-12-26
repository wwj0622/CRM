package com.topscit.springboot1.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.bean.Storage;
import com.topscit.springboot1.bean.partsGoods;
import com.topscit.springboot1.dao.GoodsMapper;
import com.topscit.springboot1.dao.PartsMapper;
import com.topscit.springboot1.dao.StorageMapper;
import com.topscit.springboot1.dao.partsGoodsMapper;
import com.topscit.springboot1.service.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService{
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private PartsMapper partsMapper;
	
    @Resource
	private StorageMapper storageMapper;
    
	@Resource
	private partsGoodsMapper partsGoodsMapper;

	@Override
	public PageInfo<Goods> SelectGoods(int pn, int size) {

        PageHelper.startPage(pn, size);
        
        List<Goods> listGoods = goodsMapper.ListGoods();
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(listGoods);
		
		return pageInfo;
	}

	@Override
	public Goods selectGoods(String id) {
        
		Goods selectGoods = goodsMapper.goodStorgePatrs(id);		
		return selectGoods;
	}

	@Override
	public PageInfo<Parts> selectGoodsID(String id,int pn,int size) {
         
		PageHelper.startPage(pn, size);
		List<Parts> selectParts = partsMapper.selectParts(id,pn,size);
        PageInfo<Parts> pageInfo = new PageInfo<Parts>(selectParts);
		
		return pageInfo;
	}

	@Override
	public List<Storage> selectStorage() {
        
		List<Storage> selectStorage = storageMapper.selectStorage();
		
		return selectStorage;
	}

	@Override
	public List<Parts> selectParts() {

        List<Parts> selectPartsList = partsMapper.selectPartsList();
		
		return selectPartsList;
	}

	@Override
	@Transactional
	public boolean insertGoods(Goods goods) {
        goods.setGstate("0");
		String[] par = goods.getPar();
		
		partsGoods record = new partsGoods();
		if(par!=null)
		{
			for (int i = 0; i < par.length; i++) 
			{
				record.setId(UUID.randomUUID().toString());
				String gid2 = goods.getGid();
				record.setGid(gid2);
				record.setPid(par[i]);
				int goodsParts = partsGoodsMapper.insertSelective(record);			
			}
		}
		int g = goodsMapper.insertSelective(goods);
 
		return true;
	}

	@Override
	public boolean deleteGoods(String id) {

        int updateGoods = goodsMapper.UpdateGoods(id);
		
        if(updateGoods==1)
        {
        	return true;
        }
        else {
        	return false;
		}
        
		
	}
	

	public Goods EidtGoodsID(String id) {
       
        Goods selectGoods = goodsMapper.goodStorgePatrs(id);
		
		return selectGoods;
	}

	@Override
	public boolean GoodsUpdate(Goods goods) {
        
        int deleteGoodsParts = partsGoodsMapper.DeleteGoodsParts(goods.getGid());
		
        String[] par = goods.getPar();
        
        partsGoods partsGoods = new partsGoods();
        for (int i = 0; i < par.length; i++) {
			partsGoods.setId(UUID.randomUUID().toString());
        	partsGoods.setGid(goods.getGid());
        	partsGoods.setPid(par[i]);
        	int insertSelective = partsGoodsMapper.insertSelective(partsGoods);
        }
        
        int updateByPrimaryKeySelective = goodsMapper.updateByPrimaryKeySelective(goods);
        System.out.println(updateByPrimaryKeySelective+"goods更新");        
		return true;
	}

	@Transactional
	@Override
	public boolean insertParts(Parts parts){      
		partsGoods partsGoods = new partsGoods();
		partsGoods.setId(UUID.randomUUID().toString());
		partsGoods.setGid(parts.getGid());
		partsGoods.setPid(parts.getPid());
		partsGoodsMapper.insertSelective(partsGoods);
		
		parts.setPdanger("5");
		parts.setPstate("0");
		parts.setPupdateTime(new Date());
        parts.setPunit("个");
        parts.setPcreateTime(new Date());
		int insertSelective = partsMapper.insertSelective(parts);
		
        return true;
       
    
	}
    @Transactional
	@Override
	public boolean UpdateParts(partsGoods partsGoods) {
        
        int deleteGoodsParts = partsGoodsMapper.GoodsParts(partsGoods.getPid(),partsGoods.getGid());
	    
		return true;
	}


	@Override
	public PageInfo<Parts> PagePartsALL(int pn, int size) {
        
		PageHelper.startPage(pn, size);
		List<Parts> selectAllParts = partsMapper.selectAllParts();
		
        PageInfo<Parts> pageInfo = new PageInfo<Parts>(selectAllParts);
        
		return pageInfo;
	}

	@Override
	public boolean DeleteParts(String id) {
        
		boolean updataPartastate = partsMapper.UpdataPartastate(id);

		return updataPartastate;
	}

	@Override
	public boolean addParts(Parts parts) {
         
		int insertSelective = partsMapper.insertSelective(parts);

		
		return true;
	}
	

	

	
	

}
