package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.service.LoginService;
import com.hsyuan.inventropy.service.UserService;
import com.hsyuan.inventropy.service.impl.LoginServiceImpl;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;
    @PostMapping
    public Result login(@RequestBody Map<String,Object> params) {

        LoginInfo loginInfo = loginService.LoginInfo(params);
        if(loginInfo != null) {
            return Result.Ok(loginInfo);
        }
        return Result.fail("用户名或密码错误");
    }

    @GetMapping("/auth/publicKey")
    public Result getPublicKey() {
        return loginService.getPublicKey();
    }
}
