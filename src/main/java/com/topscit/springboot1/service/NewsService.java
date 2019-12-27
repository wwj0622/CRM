package com.topscit.springboot1.service;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.News;
import com.topscit.springboot1.bean.User;


public interface NewsService {

	
	PageInfo<News>  SelectNews(int pn,int size);
	
	List<User> selectUser();
	
	boolean InsertNews(News news);
	
	PageInfo<News> ReceiveNews(int pn,int size);
	
	News SelectNewsLook(String id);
	
	boolean InsertNewsReceive(News news);
	
	boolean deleteNews(String id);
	
	PageInfo<News> SelectNewsList(int pn,int size,HashMap<String,String> data);
}
