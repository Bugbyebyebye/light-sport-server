package com.sport.mapper;

import com.sport.entity.Dynamic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DynamicMapper {

    @Select("select * from t_dynamic")
    List<Dynamic> getAllDynamic();

    @Insert("insert into t_dynamic(userId,content,distance,speed,space,time,createTime) values (#{userId},#{content},#{distance},#{speed},#{space},#{time},#{date})")
    Integer addNewDynamic(Integer userId,String content,String distance,String speed,String space,String time,String date);
}
