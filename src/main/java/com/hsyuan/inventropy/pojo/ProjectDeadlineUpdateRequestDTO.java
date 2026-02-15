package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "项目截止时间更新请求参数")
public class ProjectDeadlineUpdateRequestDTO {
    @Schema(description = "项目ID", example = "1001")
    private Integer id;

    @Schema(description = "新的截止时间", example = "2026-03-01T00:00:00")
    private LocalDateTime newDeadline;

    @Schema(description = "新状态: 0-待审核, 1-进行中, 2-已完成, 3-已驳回", example = "2")
    private Integer newStatus;
}
