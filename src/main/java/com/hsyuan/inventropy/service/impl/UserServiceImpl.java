package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.entity.User;
import com.hsyuan.inventropy.mapper.ProjectMapper;
import com.hsyuan.inventropy.mapper.UserMapper;
import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.pojo.UserInformation;
import com.hsyuan.inventropy.service.UserService;
import com.hsyuan.inventropy.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
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
    public LoginInfo LoginInfo(UserDTO user) {
        UserDTO e = userMapper.selectByUserNameAndPasswordAndUserType(user);
        if (e != null) {
            //生成令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("userType",e.getUserType());
            String jwt = JwtUtils.generateToken(claims);
            return  new LoginInfo(e.getId(),e.getUserType(), jwt);
        }
        return null;
    }
}
