package com.hsyuan.inventropy.service;


import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.pojo.*;
import org.apache.ibatis.annotations.Select;

public interface UserService {

    Result getUserInfo(Integer id);


    Result getUserIngProjects(Integer id);

    Result changePassword(UserDTO user);

    Result submitProject(Project project);

    Result getUserFinishedProjects(Integer id);

    Result getUserUnpassedProjects(Integer id);

    Result applyFunds(FundsApplyDTO fundsApply);

    Result getTimeLines(Integer id);

}
