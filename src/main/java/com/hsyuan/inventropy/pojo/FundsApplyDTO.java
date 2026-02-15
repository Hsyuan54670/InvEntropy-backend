package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "经费申请 DTO")
public class FundsApplyDTO {
    @Schema(description = "经费申请ID", example = "2001")
    private Integer id;

    @Schema(description = "项目ID", example = "1001")
    private Integer projectId;

    @Schema(description = "申请人姓名", example = "张三")
    private String applicant;

    @Schema(description = "申请理由", example = "购买实验材料")
    private String reason;

    @Schema(description = "申请经费金额", example = "5000.00")
    private Double appliedFunds;

    @Schema(description = "剩余经费", example = "15000.00")
    private Double remainingFunds;

    @Schema(description = "申请人ID", example = "1001")
    private Integer applicantId;

    @Schema(description = "申请时间", example = "2026-02-15T10:30:00")
    private LocalDateTime appliedTime;
}
