package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundsApplyDTO {
    private Integer projectId;
    private String applicant;
    private String reason;
    private Double appliedFunds;
    private Double remainingFunds;
    private Integer applicantId;
    private LocalDateTime appliedTime;
}
