package com.topscit.springboot1.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.management.MalformedObjectNameException;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Buy;
import com.topscit.springboot1.bean.BuyDetail;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.dao.BuyDetailMapper;
import com.topscit.springboot1.dao.BuyMapper;
import com.topscit.springboot1.dao.PartsMapper;
import com.topscit.springboot1.service.BuyService;

import scala.annotation.meta.setter;

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
	public Buy getBuyBybid(String bid) {
		Buy selectByPrimaryKey = buyMapper.getBuyBy(bid);
		return selectByPrimaryKey;
	}
	
	
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
		
		buy.setBstate("0");
		
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
		buyDetail.setBdstate("0");
		
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
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
		Date endTime = null;
		Date beginTime = null;
		
		try {
			endTime = simpleDateFormat.parse(endDate);
			beginTime = simpleDateFormat.parse(beginDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Buy> allBuy = mapper.getBuyByTime(beginTime, endTime);
		PageInfo<Buy> pageInfo = new PageInfo<Buy>(allBuy);
		return pageInfo;
	}

	@Override
	public List<BuyDetail> selectBuyDetailByTime(String beginDate, String endDate) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
		Date endTime = null;
		Date beginTime = null;
		
		try {
			endTime = simpleDateFormat.parse(endDate);
			beginTime = simpleDateFormat.parse(beginDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<BuyDetail> buyDetailByTime = buyDetailMapper.getBuyDetailByTime(beginTime, endTime);
		return buyDetailByTime;
	}

	@Override
	public boolean updateBuyByBid(Buy buy) {
		int updateByPrimaryKeySelective = buyMapper.updateByPrimaryKeySelective(buy);
		if(updateByPrimaryKeySelective == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public boolean updateBuyDetail(BuyDetail buydetail) {
		int updateByPrimaryKeySelective = buyDetailMapper.updateByPrimaryKeySelective(buydetail);
		if(updateByPrimaryKeySelective == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}


	@Override
	public boolean updateStateByBid(String bid) {
		int updateStateByBid = buyDetailMapper.updateStateByBid(bid);
		if(updateStateByBid == 1)
		{
			return true;
		}
		else{
			return false;
		}	}


	@Override
	public PageInfo<Buy> selectBuyInList(int pn, int size) {
		BuyMapper mapper = st.getMapper(BuyMapper.class);
		PageHelper.startPage(pn,size);
		List<Buy> allBuy = mapper.getAllBuyIn();
		PageInfo<Buy> pageInfo = new PageInfo<Buy>(allBuy);
		return pageInfo;
	}


	@Override
	public PageInfo<Parts> selectPartsListBy(int pn, int size) {
		PartsMapper mapper = st.getMapper(PartsMapper.class);
		PageHelper.startPage(pn,size);
		List<Parts> partsBy = mapper.getPartsBy();
		PageInfo<Parts> pageInfo = new PageInfo<Parts>(partsBy);
		return pageInfo;
	}


	@Override
	public PageInfo<BuyDetail> selectBuyDetailList(int pn, int size) {
		BuyDetailMapper mapper = st.getMapper(BuyDetailMapper.class);
		PageHelper.startPage(pn,size);
		List<BuyDetail> buyDetailAll = mapper.getBuyDetailAll();
		PageInfo<BuyDetail> pageInfo = new PageInfo<BuyDetail>(buyDetailAll);
		return pageInfo;
	}


	@Override
	public boolean updateCount(String count, String pid) {
		int updateCount = partsMapper.updateCount(count, pid);
		if(updateCount == 1)
		{
			return true;
		}
		else{
			return false;
		}
	}




	
}
