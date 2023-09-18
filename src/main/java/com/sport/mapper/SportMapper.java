package com.sport.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SportMapper {
    @Insert("insert into t_sport(userId,distance,speed,time,step,calorie) values(#{userId},#{distance},#{speed},#{time},#{step},#{calorie});")
    Integer addNewSport(Integer userId,String distance,String speed,String time,String step,String calorie);
}
