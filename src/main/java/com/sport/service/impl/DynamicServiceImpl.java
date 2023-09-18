package com.sport.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.sport.entity.Dynamic;
import com.sport.entity.User;
import com.sport.mapper.DynamicMapper;
import com.sport.mapper.UserMapper;
import com.sport.service.DynamicService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 动态服务
 */
@Slf4j
@Service
public class DynamicServiceImpl implements DynamicService {

    @Resource
    DynamicMapper dynamicMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 查询动态信息
     * @return
     */
    @Override
    public Result getDynamicInfo() {
        Result result = new Result();
        JSONObject res = new JSONObject();
        JSONArray data = new JSONArray();

        List<Dynamic> dynamics = dynamicMapper.getAllDynamic();
        for(int i=0;i<dynamics.size();i++){
            JSONObject item = new JSONObject();

            Integer userId = dynamics.get(i).getUserId();
            User user = userMapper.getUserById(userId);
            String nickname = user.getNickname();
            String avatar = user.getAvatar();

            item.put("_id",dynamics.get(i).getId());
            item.put("userId",dynamics.get(i).getUserId());
            item.put("nickname",nickname);
            item.put("avatar",avatar);
            item.put("content",dynamics.get(i).getContent());
            item.put("space",dynamics.get(i).getSpace());
            item.put("distance",dynamics.get(i).getDistance());
            item.put("time",dynamics.get(i).getTime());
            item.put("speed",dynamics.get(i).getSpeed());
            item.put("createTime",dynamics.get(i).getCreateTime());

            data.set(i,item);
        }

        res.put("data",data);
        result.setCode(200);
        result.setMessage("查询动态消息成功");
        result.setRes(res);

        return result;
    }

    /**
     * 设置动态信息
     * @return
     */
    @Override
    public Result setDynamicInfo(String json) {
        log.info("json:{}", json);
        JSONObject params = JSONObject.parseObject(json);

        log.info("params {}",params);

        Result result = new Result();
        //JSONObject res = new JSONObject();
        //JSONArray data = new JSONArray();

        String id = params.get("id").toString();
        String content = params.get("content").toString();
        String space = params.get("space").toString();
        String time = "";
        String distance = "";
        String speed = "";
        if(params.size() > 3){
            distance = params.get("distance").toString();
            speed = params.get("speed").toString();
            time = params.get("time").toString();
        }

        long date = new Date().getTime();

        result.setCode(200);
        result.setMessage("新增动态信息成功");

        dynamicMapper.addNewDynamic(Integer.parseInt(id),content,distance,speed,space,time,Long.toString(date));

        return result;
    }
}
