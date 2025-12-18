package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.service.UserService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping("/homes/userInfo")
    public Result getUserInfo() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserInfo(id);
    }

    @GetMapping("/homes/userIngProjects")
    public Result getUserIngProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserIngProjects(id);
    }
    @PutMapping("/user/changePassword")
    public Result changePassword(@RequestBody UserDTO user) {
        user.setId((Integer) ThreadLocalUtils.get());
        return userService.changePassword(user);
    }
    @GetMapping("/home")
    public Result home() {
        return Result.Ok("欢迎来到主页");
    }

    @PostMapping("/project/submit")
    public Result submitProject(@RequestBody Project project) {
        return userService.submitProject(project);
    }
    @GetMapping("/project/getIngProjects")
    public Result getIngProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserIngProjects(id);
    }
    @GetMapping("/project/getFinishedProjects")
    public Result getFinishedProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserFinishedProjects(id);
    }
    @GetMapping("/project/getUnpassedProjects")
    public Result getUnpassedProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserUnpassedProjects(id);
    }
}
