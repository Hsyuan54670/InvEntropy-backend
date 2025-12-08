package com.hsyuan.inventropy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String projectName;
    private String projectType;
    private String applicant;
    private Long applicantId;
    private Double funds;
    private Double remainingFunds;
    private String content;
    private String startTime;
    private String deadline;
    private String updateTime;
    private Integer status;
    private String reason;
}
