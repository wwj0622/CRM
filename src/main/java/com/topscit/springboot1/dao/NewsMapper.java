package com.topscit.springboot1.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topscit.springboot1.bean.News;

import scala.annotation.meta.beanGetter;

public interface NewsMapper {
    int deleteByPrimaryKey(String nid);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String nid);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
    
    List<News> SelectAll(String id);
    
    List<News> ReceiveNews(String id);
    
    News NewsLookID(String id);
    
    boolean UpdateNewsState(String id);
    
    List<News> SelectNewsDate(@Param("parse1")Date parse1,@Param("parse2")Date parse2,@Param("name")String name);
    
}