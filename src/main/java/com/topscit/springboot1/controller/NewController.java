package com.topscit.springboot1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topscit.springboot1.bean.News;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.service.NewsService;


@Controller
@RequestMapping("/news")
public class NewController {

	@Resource
	private NewsService newsService;
	
	
	@RequestMapping("/select")
	@ResponseBody
	public PageInfo<News> SelectList(
			@RequestParam(defaultValue="1") int pn,
			@RequestParam(defaultValue="3") int size
			)
	{
		PageInfo<News> selectNews = newsService.SelectNews(pn, size);
		
		return selectNews;
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public List<User> SelectUser()
	{
		 List<User> selectUser = newsService.selectUser();
		 
		 return selectUser;
	}
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public HashMap<String, Object> InsertNews(@RequestBody News news)
	{
		boolean insertNews = newsService.InsertNews(news);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", insertNews);
		
		return hashMap;
		
	}
	
	@RequestMapping("/receive")
	@ResponseBody
	public PageInfo<News> ReceiveNews(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="2")int size
			)
	{
		PageInfo<News> receiveNews = newsService.ReceiveNews(pn, size);
		
		return receiveNews;
	}
	
	
	@RequestMapping("/LookJSP")
	public String NewsLook(String id,Map<String, Object> data)
	{
    	data.put("id", id);
		return "forward:/News_Look.jsp";
		
	}
	
	
	@RequestMapping("LookId")
	@ResponseBody
	public News NewsLookId(String id)
	{	
		News selectNewsLook = newsService.SelectNewsLook(id);
	
		return selectNewsLook;
	}
	
	
	@RequestMapping("/receiveNews")
	@ResponseBody
	public HashMap<String, Object> InsertNewsReceive(@RequestBody News news)
	{
		boolean insertNewsReceive = newsService.InsertNewsReceive(news);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", insertNewsReceive);
		return hashMap;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String, Object> DelectNews(String id)
	{

		boolean deleteNews = newsService.deleteNews(id);
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("state", deleteNews);
		
		return hashMap;
	}
	
	@RequestMapping("/selectDate")
	@ResponseBody
	public PageInfo<News> SelectNewsList(
			@RequestParam(defaultValue="1")int pn,
			@RequestParam(defaultValue="3")int size,
			@RequestBody HashMap<String, String> data
			)
	{
		PageInfo<News> selectNewsList = newsService.SelectNewsList(pn, size, data);
		
		return selectNewsList;
	}
	
}
