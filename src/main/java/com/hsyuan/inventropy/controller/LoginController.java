package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping
    public Result login(@RequestBody UserDTO user) {
        LoginInfo loginInfo = userService.LoginInfo(user);
        if(loginInfo != null) {
            return Result.Ok(loginInfo);
        }
        return Result.fail("用户名或密码错误");
    }

}
