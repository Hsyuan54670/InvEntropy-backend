package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "经费审批请求参数")
public class FundsApprovalRequestDTO {
    @Schema(description = "审批意见", example = "同意")
    private String comment;

    @Schema(description = "审批金额", example = "5000.00")
    private Double appliedFunds;
}
