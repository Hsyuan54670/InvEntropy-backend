package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.*;
import com.hsyuan.inventropy.service.AdminService;
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

import java.util.Map;

@RestController
@RequestMapping
@Tag(name = "管理员模块", description = "管理员审批、日志与账号管理接口")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(responseCode = "400", description = "请求失败", content = @Content(schema = @Schema(implementation = Result.class)))
})
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/admin/projectsApprovalList")
    @Operation(summary = "获取项目审批列表", description = "分页获取待审批项目列表")
    public Result getProjectsApprovalList(
            @Parameter(description = "页码", example = "1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "每页条数", example = "5") @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize) {
        return adminService.getProjectsApprovalList(page, pageSize);
    }

    @PutMapping("/admin/approvalProject/{id}")
    @Operation(summary = "通过项目审批", description = "管理员审核通过指定项目")
    public Result approveProject(@Parameter(description = "项目ID", example = "1001") @PathVariable Integer id) {
        return adminService.approveProject(id);
    }

    @PutMapping("/admin/notApprovalProject/{id}")
    @Operation(summary = "驳回项目审批", description = "管理员审核驳回指定项目并给出原因")
    public Result notApprovalProject(
            @Parameter(description = "项目ID", example = "1001") @PathVariable Integer id,
            @Parameter(description = "驳回原因", example = "材料不完整") @RequestParam String reason) {
        return adminService.notApproveProject(id, reason);
    }

    @GetMapping("/admin/fundsApprovalList")
    @Operation(summary = "获取经费审批列表", description = "分页获取待审批经费申请列表")
    public Result getFundsApprovalList(
            @Parameter(description = "页码", example = "1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "每页条数", example = "5") @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize) {
        return adminService.getFundsApprovalList(page, pageSize);
    }

    @PutMapping("/admin/approvalFunds/{id}")
    @Operation(
            summary = "通过经费审批",
            description = "管理员审核通过经费申请",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FundsApprovalRequestDTO.class))
            )
    )
    public Result approveFunds(@Parameter(description = "申请ID", example = "2001") @PathVariable Integer id,
                               @RequestBody FundsApprovalRequestDTO request) {
        return adminService.approveFunds(id, request.getComment(), request.getAppliedFunds());
    }

    @PutMapping("/admin/notApprovalFunds/{id}")
    @Operation(
            summary = "驳回经费审批",
            description = "管理员审核驳回经费申请",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RejectRequestDTO.class))
            )
    )
    public Result notApprovalFunds(@Parameter(description = "申请ID", example = "2001") @PathVariable Integer id,
                                   @RequestBody RejectRequestDTO request) {
        return adminService.notApproveFunds(id, request.getComment());
    }

    @GetMapping("/admin/projectsLogList")
    @Operation(summary = "获取项目日志列表", description = "分页获取项目审批日志")
    public Result getProjectsLogList(
            @Parameter(description = "页码", example = "1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "每页条数", example = "5") @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize) {
        return adminService.getProjectsLogList(page, pageSize);
    }

    @GetMapping("/admin/fundsLogList")
    @Operation(summary = "获取经费日志列表", description = "分页获取经费审批日志")
    public Result getFundsLogList(
            @Parameter(description = "页码", example = "1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "每页条数", example = "5") @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize) {
        return adminService.getFundsLogList(page, pageSize);
    }

    @GetMapping("/admin/projectsList")
    @Operation(summary = "获取项目列表", description = "分页获取所有项目列表")
    public Result getProjectsList(
            @Parameter(description = "页码", example = "1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "每页条数", example = "5") @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize) {
        return adminService.getProjectsList(page, pageSize);
    }

    @PutMapping("/project/updateProjectStatus")
    @Operation(
            summary = "更新项目状态",
            description = "修改项目状态",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectStatusUpdateRequestDTO.class))
            )
    )
    public Result updateProjectStatus(@RequestBody ProjectStatusUpdateRequestDTO params) {
        return adminService.updateProjectStatus(params.getId(), params.getNewStatus());
    }

    @DeleteMapping("/project/deleteProject/{id}")
    @Operation(summary = "删除项目", description = "删除指定项目")
    public Result deleteProject(@Parameter(description = "项目ID", example = "1001") @PathVariable Integer id) {
        return adminService.deleteProject(id);
    }

    @PutMapping("/project/updateDeadline")
    @Operation(
            summary = "更新项目截止时间",
            description = "修改项目截止时间及状态",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectDeadlineUpdateRequestDTO.class))
            )
    )
    public Result updateDeadline(@RequestBody ProjectDeadlineUpdateRequestDTO params) {
        return adminService.updateDeadline(params.getId(), params.getNewDeadline(), params.getNewStatus());
    }

    @GetMapping("/admin/work")
    @Operation(summary = "获取工作类型列表", description = "获取可选工作类型")
    public Result getWorkList() {
        return adminService.getWork();
    }

    @GetMapping("/admin/funds")
    @Operation(summary = "获取经费类型列表", description = "获取可选经费类型")
    public Result getFundsList() {
        return adminService.getFunds();
    }

    @GetMapping("/admin/kinds")
    @Operation(summary = "获取项目种类列表", description = "获取可选项目种类")
    public Result getKindsList() {
        return adminService.getKinds();
    }

    @GetMapping("/admin/accountList")
    @Operation(summary = "获取账号列表", description = "获取所有账号列表")
    public Result getAccountList() {
        return adminService.getAccountList();
    }

    @PostMapping("/admin/addAccount")
    @Operation(
            summary = "新增账号",
            description = "创建新账号",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddAccountRequestDTO.class))
            )
    )
    public Result addAccount(@RequestBody AddAccountRequestDTO params) {
        java.util.Map<String, Object> paramMap = new java.util.HashMap<>();
        paramMap.put("username", params.getUsername());
        paramMap.put("password", params.getPassword());
        return adminService.addAccount(paramMap);
    }

    @DeleteMapping("/admin/deleteAccount/{id}")
    @Operation(summary = "删除账号", description = "删除指定账号")
    public Result deleteAccount(@Parameter(description = "账号ID", example = "3001") @PathVariable Integer id) {
        return adminService.deleteAccount(id);
    }

    @GetMapping("/project/getAllProjectsByCondition")
    @Operation(summary = "条件查询项目", description = "根据多个条件筛选项目")
    public Result getAllProjectsByCondition(@Parameter(description = "查询条件集合") @RequestParam Map<String, Object> params) {
        return adminService.getAllProjectsByCondition(params);
    }

}
