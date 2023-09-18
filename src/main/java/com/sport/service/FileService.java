package com.sport.service;

import com.sport.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

public interface FileService {
    Result uploadAvatar(MultipartFile file);

    String uploadQNImg(InputStream file, String path);
}
