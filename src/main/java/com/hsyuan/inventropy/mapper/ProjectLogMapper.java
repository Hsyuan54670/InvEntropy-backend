package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.entity.ProjectLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectLogMapper {
    void insert(ProjectLog projectLog);

    List<ProjectLog> getAll();
    
    void deleteByProjectId(Integer projectId);
}
