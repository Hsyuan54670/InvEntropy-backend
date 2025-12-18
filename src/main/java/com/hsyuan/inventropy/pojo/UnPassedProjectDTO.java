package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnPassedProjectDTO {
    private String projectName;
    private String approver;
    private String reason;
    private String createTime;
    private String status;
}
