package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "项目状态更新请求参数")
public class ProjectStatusUpdateRequestDTO {
    @Schema(description = "项目ID", example = "1001")
    private Integer id;

    @Schema(description = "新状态: 0-待审核, 1-进行中, 2-已完成, 3-已驳回", example = "2")
    private Integer newStatus;
}
