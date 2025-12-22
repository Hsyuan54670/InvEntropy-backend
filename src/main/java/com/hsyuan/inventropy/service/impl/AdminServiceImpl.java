package com.hsyuan.inventropy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hsyuan.inventropy.entity.FundsLog;
import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.entity.ProjectLog;
import com.hsyuan.inventropy.mapper.FundsLogMapper;
import com.hsyuan.inventropy.mapper.ProjectLogMapper;
import com.hsyuan.inventropy.mapper.ProjectMapper;
import com.hsyuan.inventropy.pojo.*;
import com.hsyuan.inventropy.service.AdminService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectLogMapper projectLogMapper;
    @Autowired
    private FundsLogMapper fundsLogMapper;
    @Override
    public Result getProjectsApprovalList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Project> res = projectMapper.getProjectsApprovalList();
        Page<Project> p=(Page<Project>) res;
        return Result.Ok(new PageTable<Project>(page,pageSize,p.getTotal(),p.getResult()));
    }

    @Override
    public Result approveProject(Integer id) {
        projectMapper.updateProjectStatus(id, 3);
        projectLogMapper.insert(new ProjectLog(id,0,3,"审批通过", (Integer) ThreadLocalUtils.get()));
        return Result.Ok();
    }

    @Transactional
    @Override
    public Result notApproveProject(Integer id, String reason) {
        projectMapper.updateProjectStatus(id, 1);
        projectMapper.updateReason(id, reason);
        projectLogMapper.insert(new ProjectLog(id,0,1,reason, (Integer) ThreadLocalUtils.get()));
        return Result.Ok();
    }

    @Override
    public Result getFundsApprovalList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<FundsApplyDTO> res = fundsLogMapper.getFundsApprovalList();
        Page<FundsApplyDTO> p=(Page<FundsApplyDTO>) res;
        return Result.Ok(new PageTable<FundsApplyDTO>(page,pageSize,p.getTotal(),p.getResult()));
    }

    @Transactional
    @Override
    public Result approveFunds(Integer id, String comment, Double appliedFunds) {
        Integer approverId = (Integer) ThreadLocalUtils.get();
        fundsLogMapper.addMarkedById(id,approverId,comment,2);
        Integer projectId =fundsLogMapper.getFundsLogById(id).getProjectId();
        Project project = projectMapper.getProjectById(projectId);
        Double remainingFunds = project.getRemainingFunds();
        remainingFunds -= appliedFunds;
        projectMapper.updateProjectRemainingFunds(projectId,remainingFunds);
        return Result.Ok();
    }

    @Override
    public Result notApproveFunds(Integer id, String comment) {
        Integer approverId = (Integer) ThreadLocalUtils.get();
        fundsLogMapper.addMarkedById(id,approverId,comment,1);
        return Result.Ok();
    }

    @Override
    public Result getProjectsLogList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ProjectLog> res = projectLogMapper.getAll();
        Page<ProjectLog> p=(Page<ProjectLog>) res;
        return Result.Ok(new PageTable<ProjectLog>(page,pageSize,p.getTotal(),p.getResult()));
    }

    @Override
    public Result getFundsLogList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<FundsLog> res = fundsLogMapper.getAll();
        Page<FundsLog> p=(Page<FundsLog>) res;
        return Result.Ok(new PageTable<FundsLog>(page,pageSize,p.getTotal(),p.getResult()));
    }

    @Override
    public Result getProjectsList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Project> res = projectMapper.getAllPassed();
        Page<Project> p=(Page<Project>) res;
        return Result.Ok(new PageTable<Project>(page,pageSize,p.getTotal(),p.getResult()));
    }

    @Override
    public Result updateProjectStatus(Integer id, Integer newStatus) {
        projectMapper.updateProjectStatus(id, newStatus);
        Integer oldStatus = projectMapper.getProjectStatusById(id);
        projectLogMapper.insert(new ProjectLog(id,oldStatus,newStatus,"经过讨论决定", (Integer) ThreadLocalUtils.get()));
        return Result.Ok();
    }

    @Transactional
    @Override
    public Result deleteProject(Integer id) {
        // 先删除与项目关联的资金日志记录
        fundsLogMapper.deleteByProjectId(id);
        // 再删除与项目关联的项目日志记录
        projectLogMapper.deleteByProjectId(id);
        // 最后删除项目本身
        projectMapper.deleteProjectById(id);
        return Result.Ok();
    }

    @Transactional
    @Override
    public Result updateDeadline(Integer id, LocalDateTime newDeadline, Integer newStatus) {
        projectMapper.updateProjectStatus(id, newStatus);
        projectMapper.updateDeadline(id, newDeadline);
        return Result.Ok();
    }

    @Override
    public Result getWork() {
        Long projectsRecords = projectMapper.countProjectsByStatus(0);
        Long fundsRecords = fundsLogMapper.countFundsUnmarked();
        Long projectsOverTime = projectMapper.countProjectsByStatus(2);
        return Result.Ok(new WorkCount(projectsRecords,fundsRecords,projectsOverTime));
    }

    @Override
    public Result getFunds() {
        Double totalFunds = projectMapper.getTotalFunds();
        Double totalRemainingFunds = projectMapper.getTotalRemainingFunds();
        return Result.Ok(new FundsCount(totalFunds,totalRemainingFunds));
    }

    @Override
    public Result getKinds() {
        Long projects = projectMapper.countProjects();
        Long projectsOf2 = projectMapper.countProjectsByStatus(2);
        Long projectsOf3 = projectMapper.countProjectsByStatus(3);
        Long projectsOf4 = projectMapper.countProjectsByStatus(4);
        Long projectsOf5 = projectMapper.countProjectsByStatus(5);
        return Result.Ok(new ProjectsKindsCount(projects,projectsOf2,projectsOf3,projectsOf4,projectsOf5));
    }


}
