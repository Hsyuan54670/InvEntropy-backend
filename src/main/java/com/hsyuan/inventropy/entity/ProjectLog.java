package com.hsyuan.inventropy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLog {
    private Integer id;
    private Integer projectId;
    private Integer oldStatus;
    private Integer newStatus;
    private String reason;
    private LocalDateTime createTime;
    private Integer approverId;
}
