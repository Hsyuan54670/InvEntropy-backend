package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.service.AdminService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/admin/projectsApprovalList")
    public Result getProjectsApprovalList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                          @RequestParam(required = false,defaultValue = "5",name="size") Integer pageSize){
        return adminService.getProjectsApprovalList(page,pageSize);
    }
    @PutMapping("/admin/approvalProject/{id}")
    public Result approveProject(@PathVariable Integer id){
        return adminService.approveProject(id);
    }
    @PutMapping("/admin/notApprovalProject/{id}")
    public Result notApprovalProject(@PathVariable Integer id,@RequestParam String reason){
        return adminService.notApproveProject(id,reason);
    }
    @GetMapping("/admin/fundsApprovalList")
    public Result getFundsApprovalList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                       @RequestParam(required = false,defaultValue = "5",name="size") Integer pageSize){
        return adminService.getFundsApprovalList(page,pageSize);
    }
    @PutMapping("/admin/approvalFunds/{id}")
    public Result approveFunds(@PathVariable Integer id, @RequestBody Map<String, Object> requestData){
        String comment = (String) requestData.get("comment");
        Double appliedFunds = Double.valueOf(requestData.get("appliedFunds").toString());
        return adminService.approveFunds(id, comment, appliedFunds);
    }
    @PutMapping("/admin/notApprovalFunds/{id}")
    public Result notApprovalFunds(@PathVariable Integer id,@RequestParam String comment){
        return adminService.notApproveFunds(id, comment);
    }
    @GetMapping("/admin/projectsLogList")
    public Result getProjectsLogList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                     @RequestParam(required = false,defaultValue = "5",name="size") Integer pageSize){
        return adminService.getProjectsLogList(page,pageSize);
    }
    @GetMapping("/admin/fundsLogList")
    public Result getFundsLogList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                  @RequestParam(required = false,defaultValue = "5",name="size") Integer pageSize){
        return adminService.getFundsLogList(page,pageSize);
    }
    @GetMapping("/admin/projectsList")
    public Result getProjectsList(@RequestParam(required = false,defaultValue = "1") Integer page,
                                  @RequestParam(required = false,defaultValue = "5",name="size") Integer pageSize){
        return adminService.getProjectsList(page,pageSize);
    }
    @PutMapping("/project/updateProjectStatus")
    public Result updateProjectStatus(@RequestBody Map<String, Object> params){
        Integer id = (Integer) params.get("id");
        Integer newStatus = (Integer) params.get("newStatus");
        return adminService.updateProjectStatus(id, newStatus);
    }
    @DeleteMapping("/project/deleteProject/{id}")
    public Result deleteProject(@PathVariable Integer id){
        return adminService.deleteProject(id);
    }
    @PutMapping("/project/updateDeadline")
    public Result updateDeadline(@RequestBody Map<String, Object> params){
        Integer id = (Integer) params.get("id");
        LocalDateTime newDeadline = LocalDateTime.parse((String) params.get("newDeadline"));
        Integer newStatus = (Integer) params.get("newStatus");
        return adminService.updateDeadline(id, newDeadline, newStatus);
    }

    @GetMapping("/admin/work")
    public Result getWorkList(){
        return adminService.getWork();
    }
    @GetMapping("/admin/funds")
    public Result getFundsList(){
        return adminService.getFunds();
    }
    @GetMapping("/admin/kinds")
    public Result getKindsList(){
        return adminService.getKinds();
    }
}
