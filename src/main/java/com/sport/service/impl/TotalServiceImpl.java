package com.sport.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.sport.entity.Dynamic;
import com.sport.entity.Total;
import com.sport.mapper.TotalMapper;
import com.sport.service.TotalService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TotalServiceImpl implements TotalService {

    @Resource
    TotalMapper totalMapper;

    @Override
    public Result getTotalInfo(Integer userId) {
        log.info("userId => {}",userId);

        Result result = new Result();
        JSONObject resTotal = new JSONObject();
        JSONArray data = new JSONArray();

        Total total = totalMapper.getTotalInfoById(userId);
        data.set(0, total);
        resTotal.put("data",data);
        result.setCode(200);
        result.setMessage("统计信息查询成功");
        result.setRes(resTotal);

        log.info("result => {}",result);
        return result;
    }
}
