package com.hsyuan.inventropy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "项目信息")
public class Project {
    @Schema(description = "项目ID", example = "1001")
    private Long id;

    @Schema(description = "项目名称", example = "科研项目A")
    private String projectName;

    @Schema(description = "项目类型", example = "科研类")
    private String projectType;

    @Schema(description = "申请人姓名", example = "张三")
    private String applicant;

    @Schema(description = "申请人ID", example = "1001")
    private Integer applicantId;

    @Schema(description = "项目经费总额", example = "50000.00")
    private Double funds;

    @Schema(description = "剩余经费", example = "35000.00")
    private Double remainingFunds;

    @Schema(description = "项目内容描述", example = "研究人工智能在医疗领域的应用")
    private String content;

    @Schema(description = "开始时间", example = "2026-01-01T00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "截止时间", example = "2026-12-31T23:59:59")
    private LocalDateTime deadline;

    @Schema(description = "更新时间", example = "2026-02-15T10:00:00")
    private LocalDateTime updateTime;

    @Schema(description = "项目状态: 0-待审核, 1-进行中, 2-已完成, 3-已驳回", example = "1")
    private Integer status;

    @Schema(description = "驳回原因", example = "材料不完整")
    private String reason;
}
