package com.hsyuan.inventropy.service;

import com.hsyuan.inventropy.pojo.Result;

public interface AdminService {
    Result getProjectsApprovalList();

    Result approveProject(Integer id);

    Result notApproveProject(Integer id, String reason);
}
