package com.hsyuan.inventropy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    Integer countIngProjects(Integer id);
    Integer countFinishedProjects(Integer id);
}
