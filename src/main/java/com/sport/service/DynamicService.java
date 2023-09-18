package com.sport.service;

import com.sport.utils.Result;

public interface DynamicService {
    Result getDynamicInfo();

    Result setDynamicInfo(String json);
}
