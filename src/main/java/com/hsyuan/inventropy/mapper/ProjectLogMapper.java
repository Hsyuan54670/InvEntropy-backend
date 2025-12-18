package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.entity.ProjectLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectLogMapper {
    void insert(ProjectLog projectLog);
}
