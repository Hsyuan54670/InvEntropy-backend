package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "驳回请求参数")
public class RejectRequestDTO {
    @Schema(description = "驳回原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "材料不完整")
    private String comment;
}
