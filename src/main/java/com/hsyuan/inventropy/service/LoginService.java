package com.hsyuan.inventropy.service;

import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;

import java.util.Map;

public interface LoginService {
    LoginInfo LoginInfo(Map<String, Object>  params);

    Result getPublicKey();
}
