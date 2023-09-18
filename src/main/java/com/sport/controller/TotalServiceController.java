package com.sport.controller;

import com.alibaba.fastjson2.JSONObject;
import com.sport.service.TotalService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/total")
public class TotalServiceController {

    @Resource
    TotalService totalService;

    @RequestMapping("/info")
    public Result getTotalInfo(@RequestBody String json){
        log.info("json => {}",json);

        JSONObject params = JSONObject.parseObject(json);
        String id = params.get("id").toString();
        return totalService.getTotalInfo(Integer.parseInt(id));
    }
}
