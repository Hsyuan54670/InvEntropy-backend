package com.hsyuan.inventropy.pojo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户信息 DTO")
public class UserDTO {
    @Schema(description = "用户ID", example = "1001")
    private Integer id;

    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    @Schema(description = "密码", example = "123456")
    private String password;

    @Schema(description = "用户类型", example = "user/admin")
    private String userType;
}
