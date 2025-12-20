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
    public ProjectLog(Integer projectId, Integer oldStatus, Integer newStatus, String reason, Integer approverId){
        this.projectId = projectId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.reason = reason;
        this.approverId = approverId;
    }
}
