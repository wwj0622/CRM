package com.topscit.springboot1.dao;

import com.topscit.springboot1.bean.PersonAffairs;

public interface PersonAffairsMapper {
    int deleteByPrimaryKey(String paid);

    int insert(PersonAffairs record);

    int insertSelective(PersonAffairs record);

    PersonAffairs selectByPrimaryKey(String paid);

    int updateByPrimaryKeySelective(PersonAffairs record);

    int updateByPrimaryKey(PersonAffairs record);
}