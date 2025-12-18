package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.entity.ProjectLog;
import com.hsyuan.inventropy.entity.User;
import com.hsyuan.inventropy.mapper.ProjectMapper;
import com.hsyuan.inventropy.mapper.UserMapper;
import com.hsyuan.inventropy.pojo.*;
import com.hsyuan.inventropy.service.UserService;
import com.hsyuan.inventropy.utils.JwtUtils;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;

    private ThreadLocalUtils threadLocal;
    @Override
    public Result getUserInfo(Integer id) {
        UserInformation data = new UserInformation();
        data.setId(id);
        Integer ingProjects = projectMapper.countIngProjects(id);
        data.setIngProjects(ingProjects);
        Integer finishedProjects = projectMapper.countFinishedProjects(id);
        data.setFinishedProjects(finishedProjects);
        data.setPassedProjects(ingProjects+finishedProjects);
        User user=userMapper.getUserInfo(id);
        if(user!=null){
            data.setName(user.getName());
            data.setGender(user.getGender());
            data.setAge(user.getAge());
            data.setPhone(user.getPhone());
            data.setCollege(user.getCollege());
            return Result.Ok(data);
        }
        return Result.fail("数据错误");
    }

    @Override
    public Result getUserIngProjects(Integer id) {
        List<Project> ingProjects = projectMapper.getUserIngProjects(id);
        return Result.Ok(ingProjects);
    }

    @Override
    public Result changePassword(UserDTO user) {
        boolean isOk = userMapper.changePassword(user);
        return Result.Ok();
    }

    @Override
    public Result submitProject(Project project) {
        LocalDateTime now = LocalDateTime.now();
        project.setStartTime(now);
        project.setUpdateTime(now);
        project.setStatus(0);
        project.setRemainingFunds(project.getFunds());
        projectMapper.insert(project);
        return Result.Ok();
    }

    @Override
    public Result getUserFinishedProjects(Integer id) {
        List<Project> finishedProjects = projectMapper.getUserFinishedProjects(id);
        return Result.Ok(finishedProjects);
    }

    // todo 获取未通过项目根据日志和 项目返回数据。
    @Override
    public Result getUserUnpassedProjects(Integer id) {
        return null;
    }


    @Override
    public LoginInfo LoginInfo(UserDTO user) {
        UserDTO e = userMapper.selectByUserNameAndPasswordAndUserType(user);
        if (e != null) {
            //生成令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", e.getUsername());
            claims.put("userType",e.getUserType());
            String jwt = JwtUtils.generateToken(claims);
            return  new LoginInfo(e.getUserType(), jwt);
        }
        return null;
    }

}
