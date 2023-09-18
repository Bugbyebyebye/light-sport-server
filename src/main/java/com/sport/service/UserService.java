package com.sport.service;

import com.sport.utils.Result;

public interface UserService {
    Result getUserInfoByName(String username);

    Result registerNewUser(String username,String password);

    Result getUserInfoById(Integer id);

    Result setUserInfoById(String json);
}
