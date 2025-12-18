package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


import com.hsyuan.inventropy.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    Integer countIngProjects(Integer id);
    Integer countFinishedProjects(Integer id);

    List<Project> getUserIngProjects(Integer id);
    List<Project> getUserFinishedProjects(Integer id);
    List<Project> getUnpassedProjects(Integer id);
    void insert(Project project);
}
