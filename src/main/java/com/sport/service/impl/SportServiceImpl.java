package com.sport.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.sport.entity.Dynamic;
import com.sport.entity.Total;
import com.sport.mapper.SportMapper;
import com.sport.mapper.TotalMapper;
import com.sport.service.SportService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SportServiceImpl implements SportService {

    @Resource
    SportMapper sportMapper;

    @Resource
    TotalMapper totalMapper;

    @Override
    public Result addNewSport(String json) {
        Result result = new Result();
        //JSONObject res = new JSONObject();
        //JSONArray data = new JSONArray();

        JSONObject params = JSONObject.parseObject(json);
        Integer id = Integer.parseInt(params.get("id").toString());
        String distance = params.get("distance").toString();
        String speed = params.get("speed").toString();
        String time = params.get("time").toString();
        String step = params.get("step").toString();
        String calorie = params.get("calorie").toString();

        sportMapper.addNewSport(id,distance,speed,time,step,calorie);
        Total totalInfoById = totalMapper.getTotalInfoById(id);

        int totalCalorie = Integer.parseInt(totalInfoById.getTotalCalorie());
        int totalDistance = Integer.parseInt(totalInfoById.getTotalDistance());
        int totalStep = Integer.parseInt(totalInfoById.getTotalStep());

        totalDistance += Integer.parseInt(distance);
        totalCalorie += Integer.parseInt(calorie);
        totalStep += Integer.parseInt(step);

        totalMapper.updateTotalInfo(Integer.toString(totalDistance), Integer.toString(totalCalorie), Integer.toString(totalStep));
        result.setCode(200);
        result.setMessage("运动数据保存成功");

        return result;
    }
}
