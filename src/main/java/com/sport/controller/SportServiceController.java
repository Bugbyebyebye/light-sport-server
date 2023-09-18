package com.sport.controller;

import com.sport.service.SportService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运动记录控制器
 */
@Slf4j
@RestController
@RequestMapping("/sport")
public class SportServiceController {

    @Resource
    SportService sportService;

    @RequestMapping("/add")
    public Result addNewSport(@RequestBody String json){

        log.info("json => {}",json);
        return sportService.addNewSport(json);
    }
}
