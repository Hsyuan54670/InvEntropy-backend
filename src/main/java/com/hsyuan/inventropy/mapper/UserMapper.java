package com.hsyuan.inventropy.mapper;


import com.hsyuan.inventropy.entity.User;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.pojo.UserInformation;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {


    UserDTO selectByUserNameAndPasswordAndUserType(UserDTO user);

    User getUserInfo(Integer id);

    boolean changePassword(UserDTO user);


}
