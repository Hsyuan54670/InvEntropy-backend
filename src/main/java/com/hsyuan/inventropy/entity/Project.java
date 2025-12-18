package com.hsyuan.inventropy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime startTime;
    private LocalDateTime deadline;
    private LocalDateTime updateTime;
    private Integer status;
    private String reason;
}
