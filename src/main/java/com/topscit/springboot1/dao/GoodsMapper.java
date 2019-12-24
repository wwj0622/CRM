package com.topscit.springboot1.dao;

import java.util.List;

import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Parts;

public interface GoodsMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	List<Goods> ListGoods();

	List<Goods> selectListGoods();

	List<Goods> selectListGoodsLimit(String gname);
	
	Goods selectGoods(String id);
	
	int UpdateGoods(String id);
	
	Goods goodStorgePatrs(String id);
	

	
	


}