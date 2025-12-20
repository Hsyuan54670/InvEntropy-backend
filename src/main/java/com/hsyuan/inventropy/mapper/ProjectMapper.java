package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.pojo.FundsApplyDTO;
import com.hsyuan.inventropy.pojo.UnPassedProjectDTO;
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

    List<UnPassedProjectDTO> getUnpassedProjectsWithLog(Integer id);

    List<Project> getProjectsApprovalList();

    void updateProjectStatus(Integer id, int newStatus);

    void updateReason(Integer id, String reason);

    Project getProjectById(Integer projectId);


}
