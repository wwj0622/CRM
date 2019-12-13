package com.topscit.springboot1.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.dao.BuyDetailMapper;
import com.topscit.springboot1.dao.BuyMapper;
import com.topscit.springboot1.dao.PartsMapper;
import com.topscit.springboot1.service.BuyService;

@Service("buyService")
public class BuyServiceImpl  implements BuyService {
	
	private String bid;

	@Resource
	private BuyDetailMapper buyDetailMapper;
	
	@Resource
	private PartsMapper partsMapper;
	
	@Resource
	private BuyMapper buyMapper;
	
	
	@Resource
	private SqlSessionTemplate st;
	@Override
	public PageInfo<Buy> selectBuyList(int pn, int size) {
		
		BuyMapper mapper = st.getMapper(BuyMapper.class);
		PageHelper.startPage(pn,size);
		List<Buy> allBuy = mapper.getAllBuy();
		PageInfo<Buy> pageInfo = new PageInfo<Buy>(allBuy);
		return pageInfo;
		
	}
	@Override
	public BuyDetail getBuyDetailBy(String id) {
		BuyDetail buyDetailBy = buyDetailMapper.getBuyDetailBy(id);
		return buyDetailBy;
	}
	@Override
	public List<Parts> getParts() {
		List<Parts> allParts = partsMapper.getAllParts();
		return allParts;
	}
	
	
	
	@Override
	public boolean addBuy(Buy buy) {
		
		bid = System.currentTimeMillis()+"hahah";
		buy.setBid(bid);
		Date date = new Date();
		buy.setGupdateTime(date);
		
		int insertSelective = buyMapper.insertSelective(buy);
		if(insertSelective == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean addBuyDetail(BuyDetail buyDetail) {
		
		buyDetail.setBdid(System.currentTimeMillis() + "xixiiix");
		Date date = new Date();
		buyDetail.setBdupdateTime(date);
		buyDetail.setBid(bid);
		
		int insertSelective = buyDetailMapper.insertSelective(buyDetail);
		if(insertSelective == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean deleteBuyDetailByBid(String id) {
		int deleteByBid = buyDetailMapper.deleteByBid(id);
		if(deleteByBid == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean deleteBuy(String id) {
		int deleteByPrimaryKey = buyMapper.deleteByPrimaryKey(id);
		if(deleteByPrimaryKey == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	
	@Override
	public PageInfo<Buy> selectBuyByTime(String beginDate, String endDate, int pn, int size) {
		BuyMapper mapper = st.getMapper(BuyMapper.class);
		PageHelper.startPage(pn,size);
		List<Buy> allBuy = mapper.getBuyByTime(beginDate, endDate);
		PageInfo<Buy> pageInfo = new PageInfo<Buy>(allBuy);
		return pageInfo;
	}
	
}
