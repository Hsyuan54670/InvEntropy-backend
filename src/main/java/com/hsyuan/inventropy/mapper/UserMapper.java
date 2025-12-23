package com.hsyuan.inventropy.mapper;


import com.hsyuan.inventropy.entity.User;
import com.hsyuan.inventropy.pojo.UserAccountForAdmin;
import com.hsyuan.inventropy.pojo.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    UserDTO selectByUserNameAndPasswordAndUserType(UserDTO user);

    User getUserInfo(Integer id);

    boolean changePassword(UserDTO user);


    List<UserAccountForAdmin> getAccountForAdmin();

    void insert(Integer id,String name, String phone, String college, String gender, Integer age);

    void insertUserAccount(String username, String password, String userType);

    boolean isExitsByUsername(String username);

    Integer getIdByUsername(String username);

    void deleteAccount(Integer id);

    void deleteUserInfo(Integer id);
}
