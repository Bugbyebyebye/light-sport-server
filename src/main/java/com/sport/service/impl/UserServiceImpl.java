package com.sport.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.sport.entity.Total;
import com.sport.entity.User;
import com.sport.mapper.TotalMapper;
import com.sport.mapper.UserMapper;
import com.sport.service.UserService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    TotalMapper totalMapper;

    /**
     * 用户登录
     * @param username
     * @return
     */
    @Override
    public Result getUserInfoByName(String username) {
        Result result = new Result();
        JSONObject res = new JSONObject();
        JSONArray data = new JSONArray();

        if(username == null){
            result.setCode(400);
            result.setMessage("用户名不能为空");
            return result;
        }

        User user = userMapper.getUserByName(username);
        if(user == null){
            result.setCode(401);
            result.setMessage("用户不存在");
            return result;
        }

        log.info("user => {}",user.toString());
        data.set(0, user);
        res.put("data", data);
        result.setCode(200);
        result.setMessage("查询用户数据成功");
        result.setRes(res);

        log.info("result => {}",result);
        return result;
    }

    /**
     * 注册新用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result registerNewUser(String username, String password) {
        Result result = new Result();
        JSONObject res = new JSONObject();

        userMapper.registerUser(username, password,"0","0","默认昵称","暂无地址");
        result.setCode(200);
        result.setMessage("用户新增成功");
        String id = userMapper.getUserIdByName(username);
        totalMapper.addTotalInfo(Integer.parseInt(id),"0","0","0");

        res.put("id",id);
        result.setRes(res);

        log.info("result => {}", result);
        return result;
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Override
    public Result getUserInfoById(Integer id) {
        Result result = new Result();
        JSONObject res = new JSONObject();
        JSONArray data = new JSONArray();

        User user = userMapper.getUserById(id);
        Total total = totalMapper.getTotalInfoById(id);

        if (user == null) {
            result.setCode(401);
            result.setMessage("用户不存在");
            return result;
        }

        log.info("user => {}", user.toString());
        data.set(0, user);
        data.set(1,total);
        res.put("data", data);
        result.setCode(200);
        result.setMessage("查询用户数据成功");
        result.setRes(res);

        log.info("result => {}", result);
        return result;
    }

    @Override
    public Result setUserInfoById(String json) {
        Result result = new Result();
        //JSONObject res = new JSONObject();
        //JSONArray data = new JSONArray();

        log.info("json:{}", json);
        JSONObject params = JSONObject.parseObject(json);
        log.info("params {}", params);

        String id = params.getString("id");
        String nickname = params.getString("nickname");
        String address = params.getString("address");
        String height = params.getString("height");
        String weight = params.getString("weight");

        userMapper.updateUserInfoById(Integer.parseInt(id),nickname,address,height,weight);

        result.setCode(200);
        result.setMessage("用户信息修改成功");
        log.info("result => {}", result);
        return result;
    }


}
