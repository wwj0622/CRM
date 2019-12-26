package com.topscit.springboot1.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.Goods;
import com.topscit.springboot1.bean.Parts;
import com.topscit.springboot1.bean.Storage;
import com.topscit.springboot1.bean.partsGoods;

public interface StockService {
	
	PageInfo<Goods> SelectGoods(int pn,int size);
	
	Goods selectGoods(String id);
     
	PageInfo<Parts> selectGoodsID(String id, int pn, int size);
	
	List<Storage> selectStorage();
	
	List<Parts>  selectParts();
	
	boolean insertGoods(Goods goods);
	
	boolean deleteGoods(String id);
	
	Goods  EidtGoodsID(String id);
	
	boolean GoodsUpdate(Goods goods);
	
	boolean insertParts(Parts parts);
	
	boolean UpdateParts(partsGoods partsGoods);
	
	PageInfo<Parts>  PagePartsALL(int pn,int size);
	
	boolean DeleteParts(String id);
	
	boolean addParts(Parts parts);
	
	
	
	
}
