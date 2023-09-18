package com.sport.controller;

import com.sport.service.DynamicService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态控制器
 */
@Slf4j
@RestController
@RequestMapping("/dynamic")
public class DynamicServiceController {

    @Resource
    DynamicService dynamicService;

    /**
     * 获取全部动态
     * @return
     */
    @RequestMapping("/all")
    public Result getAllDynamic(){
        return dynamicService.getDynamicInfo();
    }

    /**
     * 新增动态
     * @param json
     * @return
     */
    @RequestMapping("/add")
    public Result addAllDynamic(@RequestBody String json){
        return dynamicService.setDynamicInfo(json);
    }
}
