package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnPassedProjectDTO {
    private Integer id;
    private String projectName;
    private String approver;
    private String reason;
    private LocalDateTime createTime;
    private String status;
}
