package com.topscit.springboot1.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.topscit.springboot1.bean.Parts;

public interface PartsMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Parts record);

    int insertSelective(Parts record);

    Parts selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Parts record);

    int updateByPrimaryKey(Parts record);
    
    List<Parts> getAllParts();
    
    List<Parts> selectParts(@Param("id")String id,@Param("pn")int pn,@Param("size")int size);
     
    List<Parts> selectPartsList();
    
    boolean UpdataPartastate(String id);
    
    List<Parts> selectAllParts();
    
    

}