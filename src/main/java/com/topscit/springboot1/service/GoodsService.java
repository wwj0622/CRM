package com.topscit.springboot1.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;

public interface GoodsService {
	
	//查询goods表全部数据
	//用PageHelper对goods全部数据进行分页
	PageInfo<Goods> selectListGoodsByPn(int pn , int size);

}
