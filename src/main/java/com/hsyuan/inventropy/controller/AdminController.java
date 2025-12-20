package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.service.AdminService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/admin/projectsApprovalList")
    public Result getProjectsApprovalList(){
        return adminService.getProjectsApprovalList();
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
    public Result getFundsApprovalList(){
        return adminService.getFundsApprovalList();
    }
}
