package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/homes/userInfo/{id}")
    public Result getUserInfo(@PathVariable Integer id) {
        return userService.getUserInfo(id);
    }
}
