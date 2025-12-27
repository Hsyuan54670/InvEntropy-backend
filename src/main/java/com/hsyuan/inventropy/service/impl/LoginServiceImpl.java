package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.mapper.UserMapper;
import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.service.LoginService;
import com.hsyuan.inventropy.utils.JwtUtils;
import com.hsyuan.inventropy.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public LoginInfo LoginInfo(Map<String,Object>  params) {
        UserDTO  user = new UserDTO();
        user.setUsername((String) params.get("username"));
        user.setUserType((String) params.get("userType"));
        String passwordRSA = (String) params.get("password");
        String password = RSAUtils.decrypt(passwordRSA);
        user.setPassword(password);
        UserDTO e = userMapper.selectByUserNameAndPasswordAndUserType(user);
        if (e != null) {
            RSAUtils .markRandomUsed();
            //生成令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("userType", e.getUserType());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getUserType(), jwt);
        }
        return null;
    }
    @Override
    public Result getPublicKey() {
        return  Result.Ok(RSAUtils.getPublicKey());
    }
}
