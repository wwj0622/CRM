package com.topscit.springboot1.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.Buy;

public interface BuyMapper {
	
    int deleteByPrimaryKey(String bid);

    int insert(Buy record);

    int insertSelective(Buy record);

    Buy selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Buy record);

    int updateByPrimaryKey(Buy record);
    
    int updateStateByBid(@Param("bid")String bid);
    
    //查询所有
    public List<Buy> getAllBuy();
    
    public List<Buy> getAllBuyIn();
    //根据交货时间查询
    public List<Buy> getBuyByTime(@Param("beginDate")Date beginDate,@Param("endDate")Date endDate);
}