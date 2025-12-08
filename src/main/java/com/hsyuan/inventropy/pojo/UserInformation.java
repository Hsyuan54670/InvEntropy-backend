package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private String college;
    private Integer passedProjects;
    private Integer finishedProjects;
}
