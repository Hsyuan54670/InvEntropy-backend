package com.hsyuan.inventropy.service;


import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.pojo.UserInformation;
import org.apache.ibatis.annotations.Select;

public interface UserService {

    Result getUserInfo(Integer id);

    LoginInfo LoginInfo(UserDTO user);
}
