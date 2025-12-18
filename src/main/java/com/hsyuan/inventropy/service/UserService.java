package com.hsyuan.inventropy.service;


import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.pojo.UserInformation;
import org.apache.ibatis.annotations.Select;

public interface UserService {

    Result getUserInfo(Integer id);

    LoginInfo LoginInfo(UserDTO user);

    Result getUserIngProjects(Integer id);

    Result changePassword(UserDTO user);

    Result submitProject(Project project);

    Result getUserFinishedProjects(Integer id);

    Result getUserUnpassedProjects(Integer id);
}
