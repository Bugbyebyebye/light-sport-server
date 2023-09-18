package com.sport.controller;

import com.sport.service.FileService;
import com.sport.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileServiceController {

    @Resource
    FileService fileService;

    @RequestMapping("/avatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file){
        return fileService.uploadAvatar(file);
    }
}
