package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.pojo.FundsApplyDTO;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.pojo.UserDTO;
import com.hsyuan.inventropy.service.UserService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Tag(name = "用户模块", description = "用户信息、项目与经费申请接口")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(responseCode = "400", description = "请求失败", content = @Content(schema = @Schema(implementation = Result.class)))
})
public class UserController {


    @Autowired
    private UserService userService;
    @GetMapping("/homes/userInfo")
    @Operation(summary = "获取用户信息", description = "获取当前登录用户的基本信息")
    public Result getUserInfo() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserInfo(id);
    }

    @GetMapping("/homes/userIngProjects")
    @Operation(summary = "获取进行中项目", description = "获取当前用户进行中的项目")
    public Result getUserIngProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserIngProjects(id);
    }

    @PutMapping("/user/changePassword")
    @Operation(
            summary = "修改密码",
            description = "修改当前用户密码",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
            )
    )
    public Result changePassword(@RequestBody UserDTO user) {
        user.setId((Integer) ThreadLocalUtils.get());
        return userService.changePassword(user);
    }

    @GetMapping("/home")
    @Operation(summary = "主页欢迎", description = "返回主页欢迎语")
    public Result home() {
        return Result.Ok("欢迎来到主页");
    }

    @PostMapping("/project/submit")
    @Operation(
            summary = "提交项目",
            description = "提交新项目申请",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            )
    )
    public Result submitProject(@RequestBody Project project) {
        return userService.submitProject(project);
    }

    @GetMapping("/project/getIngProjects")
    @Operation(summary = "获取进行中项目", description = "获取当前用户进行中的项目")
    public Result getIngProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserIngProjects(id);
    }

    @GetMapping("/project/getFinishedProjects")
    @Operation(summary = "获取已完成项目", description = "获取当前用户已完成的项目")
    public Result getFinishedProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserFinishedProjects(id);
    }

    @GetMapping("/project/getUnpassedProjects")
    @Operation(summary = "获取未通过项目", description = "获取当前用户未通过审批的项目")
    public Result getUnpassedProjects() {
        Integer id = (Integer) ThreadLocalUtils.get();
        return userService.getUserUnpassedProjects(id);
    }

    @PutMapping("/user/commitPayFunds")
    @Operation(
            summary = "申请经费",
            description = "提交经费申请",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FundsApplyDTO.class))
            )
    )
    public Result applyFunds(@RequestBody FundsApplyDTO fundsApply) {
        return userService.applyFunds(fundsApply);
    }

    @GetMapping("/project/getTimeLines/{id}")
    @Operation(summary = "获取项目时间线", description = "根据项目ID获取项目时间线")
    public Result getTimeLines(@Parameter(description = "项目ID", example = "1001") @PathVariable Integer id) {
        return userService.getTimeLines(id);
    }
}
