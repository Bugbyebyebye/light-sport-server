package com.sport.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.sport.config.CloudStorageConfig;
import com.sport.mapper.UserMapper;
import com.sport.service.FileService;
import com.sport.utils.Result;
import com.sport.utils.StringUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    CloudStorageConfig config;

    @Resource
    UserMapper userMapper;

    @Override
    public Result uploadAvatar(MultipartFile file) {
        Result result = new Result();
        //String storage = "E:\\codeWork\\Project\\light-sport-server\\avatar\\" + new Date().getTime() + ".png";
        //String storage = "/home/data/sport/avatar/" + new Date().getTime() + ".png";

        //File localFile = new File(storage);
        try {
            //file.transferTo(localFile); //把上传的文件保存至本地
            String name = file.getOriginalFilename();
            String filename = name.substring(0, name.length()-4);

            log.info(" {} 上传成功", filename);
            String imgName = StringUtil.getRandomImgName(name);
            // 上传成功，保存到七牛云
            if(!file.isEmpty()){
                log.info("开始上传");
                InputStream inputStream =  file.getInputStream();
                String path =  uploadQNImg(inputStream, imgName);
                log.info("保存路径 {}",path);
                userMapper.updateUserAvatarById(Integer.parseInt(filename),path);
            }

            //localFile.delete();
            result.setCode(200);
            result.setMessage("头像上传成功！");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            //localFile.delete();
            result.setCode(200);
            result.setMessage("头像上传失败！");
            return result;
        }
    }

    /**
     * 调用七牛云Api上传图片
     * @param file
     * @param path
     * @return
     */
    @Override
    public String uploadQNImg(InputStream file, String path) {
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        // 根据命名空间生成的上传token
        String token = auth.uploadToken(config.getQiniuBucketName());

        try{
            Response res = uploadManager.put(file,path, token,null,null);
            if(!res.isOK()){
                throw new RuntimeException("上传七牛云出错："+res.toString());
            }
            JSONObject result = JSONObject.parseObject(res.bodyString());
            log.info("result {}",result);
            return config.getQiniuDomain() + "/" + result.getString("key");
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
    }
}
