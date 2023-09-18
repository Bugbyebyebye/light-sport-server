package com.sport.mapper;

import com.sport.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @Select("select * from t_user where id = #{id}")
    User getUserById(Integer id);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Select("select * from t_user where username = #{username}")
    User getUserByName(String username);

    /**
     * 注册新用户
     * @param username
     * @param password
     * @return
     */
    @Insert("insert into t_user(username,password,height,weight,nickname,address) values (#{username},#{password},#{height},#{weight},#{nickname},#{address})")
    Integer registerUser(String username,String password,String height,String weight,String nickname,String address);

    /**
     * 根据用户名获取用户id
     * @param username
     * @return
     */
    @Select("select id from t_user where username = #{username}")
    String getUserIdByName(String username);

    /**
     * 根据用户id更新用户信息
     * @param userId
     * @param nickname
     * @param address
     * @param height
     * @param weight
     * @return
     */
    @Update("update t_user set nickname=#{nickname},address=#{address},height=#{height},weight=#{weight} where id=#{userId};")
    Integer updateUserInfoById(Integer userId,String nickname,String address,String height,String weight);

    /**
     * 根据用户id更新avatar
     * @param userId
     * @param avatar
     * @return
     */
    @Update("update t_user set avatar=#{avatar} where id = #{userId};")
    Integer updateUserAvatarById(Integer userId,String avatar);
}
