package com.hsyuan.inventropy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundsLog {
    private Integer id;
    private Integer projectId;
    private Integer applicantId;
    private String reason;
    private Double appliedFunds;
    private LocalDateTime appliedTime;
    private Integer approverId;
    private String comment;
    private LocalDateTime updateTime;
    private Integer status;
}
