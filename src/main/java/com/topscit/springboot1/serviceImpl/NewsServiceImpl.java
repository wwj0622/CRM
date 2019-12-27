package com.topscit.springboot1.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.News;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.dao.NewsMapper;
import com.topscit.springboot1.dao.UserMapper;
import com.topscit.springboot1.dao.userRoleMapper;
import com.topscit.springboot1.service.NewsService;

import scala.Option;


@Service("newsService")
public class NewsServiceImpl implements NewsService{
	

	@Resource
	private NewsMapper newsMapper;
	
	@Resource
	private UserMapper userMapper;

	@Override
	public PageInfo<News> SelectNews(int pn,int size) {
        
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		PageHelper.startPage(pn, size);
		List<News> selectAll = newsMapper.SelectAll(user.getId());
		PageInfo<News> pageInfo = new PageInfo<News>(selectAll);
		
		return pageInfo;
	}

	@Override
	public List<User> selectUser() {
        
		Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<User> newsUser = userMapper.NewsUser(user.getId());
        	
		return newsUser;
	}

	@Override
	public boolean InsertNews(News news) {
       
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		news.setUserid(user.getId());
		news.setSendtime(new Date());
		news.setNid(UUID.randomUUID().toString());
		news.setNstate("0");
		
        int insertSelective = newsMapper.insertSelective(news);
		
		return true;
	}

	@Override
	public PageInfo<News> ReceiveNews(int pn,int size) {
        
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		PageHelper.startPage(pn, size);
        List<News> receiveNews = newsMapper.ReceiveNews(user.getId());
        PageInfo<News> pageInfo = new PageInfo<News>(receiveNews);
		
		return pageInfo;
	}
    
	@Transactional
	@Override
	public News SelectNewsLook(String id) {
       
		News news = new News();
		news.setNid(id);
		news.setReceivetime(new Date());
		news.setNstate("1");
		newsMapper.updateByPrimaryKeySelective(news);
		
        News newsLookID = newsMapper.NewsLookID(id);
		
		return newsLookID;
	}

	@Override
	public boolean InsertNewsReceive(News news) {
        
		Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
		news.setUserid(user.getId());
		news.setSendtime(new Date());
		news.setNstate("0");
		news.setNid(UUID.randomUUID().toString());
		
		Random random = new Random();
		int r = random.nextInt(20);
		String i = String.valueOf(r);
		news.setNewnumber(i);
		
		int insertSelective = newsMapper.insertSelective(news);
    
		return true;
	}

	@Override
	public boolean deleteNews(String id) {
        
	    boolean updateNewsState = newsMapper.UpdateNewsState(id);

		return updateNewsState;
	}

	@Override
	public PageInfo<News> SelectNewsList(int pn, int size,HashMap<String, String> data) {
        
		 String datemin = data.get("datemin");
		 String datemax = data.get("datemax");
		 String name = data.get("content");
		 String pn1 = data.get("pn");
		 Integer a = Integer.valueOf(pn1);
		 
		 String pattern="yyyy-MM-dd";
		 String name2="%"+name+"%";
		 Date parse1 = null;
		 Date parse2 = null;
		 SimpleDateFormat format = new SimpleDateFormat(pattern);
		 PageInfo<News> pageInfo = null;
		 try {
			if(!datemin.equals(""))
			{
			   parse1 = format.parse(datemin);
			}
			
			if(!datemax.equals(""))
			{
			  parse2= format.parse(datemax);
			}
			
			PageHelper.startPage(a, size);
			List<News> selectNewsDate = newsMapper.SelectNewsDate(parse1, parse2, name2);
		    pageInfo = new PageInfo<News>(selectNewsDate);
					
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		return pageInfo;
		 	
	}
	
	
	
	  
}
