package com.sport.mapper;

import com.sport.entity.Total;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TotalMapper {
    @Select("select * from t_total where userId = #{userId};")
    Total getTotalInfoById(Integer userId);

    @Insert("insert into t_total(userId,totalDistance,totalCalorie,totalStep) values (#{userId},#{totalDistance},#{totalCalorie},#{totalStep})")
    Integer addTotalInfo(Integer userId, String totalDistance, String totalCalorie, String totalStep);

    @Update("update t_total set totalDistance=#{totalDistance},totalCalorie=#{totalCalorie},totalStep=#{totalStep}")
    Integer updateTotalInfo(String totalDistance, String totalCalorie, String totalStep);
}
