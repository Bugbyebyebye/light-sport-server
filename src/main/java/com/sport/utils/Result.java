package com.sport.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/*
    结果类
 */
@Data
public class Result {
    Integer code;

    String message;

    JSONObject res;
}
