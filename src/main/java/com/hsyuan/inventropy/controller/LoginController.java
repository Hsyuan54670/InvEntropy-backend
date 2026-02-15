package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.LoginInfo;
import com.hsyuan.inventropy.pojo.LoginRequestDTO;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.service.LoginService;
import com.hsyuan.inventropy.service.impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户认证模块", description = "用户登录、注册、鉴权相关接口")
@RestController
@RequestMapping("/login")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(responseCode = "400", description = "请求失败", content = @Content(schema = @Schema(implementation = Result.class)))
})
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    @Operation(
            summary = "用户账号密码登录",
            description = "接收用户名和密码，校验通过后返回登录Token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequestDTO.class))
            )
    )
    @PostMapping
    public Result login(@RequestBody LoginRequestDTO loginRequest) {
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("username", loginRequest.getUsername());
        params.put("password", loginRequest.getPassword());
        params.put("userType", loginRequest.getUserType());

        LoginInfo loginInfo = loginService.LoginInfo(params);
        if(loginInfo != null) {
            return Result.Ok(loginInfo);
        }
        return Result.fail("用户名或密码错误");
    }

    @Operation(summary = "获取公钥", description = "获取登录加密公钥")
    @GetMapping("/auth/publicKey")
    public Result getPublicKey() {
        return loginService.getPublicKey();
    }
}
