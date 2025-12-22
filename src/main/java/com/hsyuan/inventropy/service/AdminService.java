package com.hsyuan.inventropy.service;

import com.hsyuan.inventropy.pojo.Result;

import java.time.LocalDateTime;

public interface AdminService {
    Result getProjectsApprovalList(Integer page, Integer pageSize);

    Result approveProject(Integer id);

    Result notApproveProject(Integer id, String reason);

    Result getFundsApprovalList(Integer page, Integer pageSize);

    Result approveFunds(Integer id, String comment, Double appliedFunds);

    Result notApproveFunds(Integer id, String comment);

    Result getProjectsLogList(Integer page, Integer pageSize);

    Result getFundsLogList(Integer page, Integer pageSize);

    Result getProjectsList(Integer page, Integer pageSize);

    Result updateProjectStatus(Integer id, Integer newStatus);

    Result deleteProject(Integer id);

    Result updateDeadline(Integer id, LocalDateTime newDeadline, Integer newStatus);

    Result getWork();

    Result getFunds();

    Result getKinds();
}
