package com.sport.controller;

import com.alibaba.fastjson2.JSONObject;
import com.sport.service.UserService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务控制器
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserServiceController {

    @Resource
    UserService userService;

    /**
     * 用户登录
     * 根据用户名获取用户信息
     * @param json
     * @return
     */
    @RequestMapping("/login")
    Result login(@RequestBody String json){
        log.info("json:{}",json);
        JSONObject params = JSONObject.parseObject(json);

        return userService.getUserInfoByName(params.get("username").toString());
    }

    /**
     * 用户注册
     * @param json
     * @return
     */
    @RequestMapping("/register")
    Result register(@RequestBody String json){
        log.info("json:{}", json);
        JSONObject params = JSONObject.parseObject(json);
        String username = params.get("username").toString();
        String password = params.get("password").toString();

        return userService.registerNewUser(username,password);
    }

    /**
     * 根据id获取用户信息
     * @param json
     * @return
     */
    @RequestMapping("/getInfo")
    Result getUserInfo(@RequestBody String json){
        log.info("json:{}", json);
        JSONObject params = JSONObject.parseObject(json);
        String id = params.get("id").toString();
        return userService.getUserInfoById(Integer.parseInt(id));
    }

    @RequestMapping("/setInfo")
    Result setUserInfo(@RequestBody String json){
        return userService.setUserInfoById(json);
    }
}
