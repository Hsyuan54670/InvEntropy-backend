package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.entity.ProjectLog;
import com.hsyuan.inventropy.mapper.FundsLogMapper;
import com.hsyuan.inventropy.mapper.ProjectLogMapper;
import com.hsyuan.inventropy.mapper.ProjectMapper;
import com.hsyuan.inventropy.pojo.FundsApplyDTO;
import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.service.AdminService;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Result getProjectsApprovalList() {
        List<Project> res = projectMapper.getProjectsApprovalList();
        return Result.Ok(res);
    }

    @Override
    public Result approveProject(Integer id) {
        projectMapper.updateProjectStatus(id, 3);
        projectLogMapper.insert(new ProjectLog(id,0,3,"", (Integer) ThreadLocalUtils.get()));
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
    public Result getFundsApprovalList() {
        List<FundsApplyDTO> res = fundsLogMapper.getFundsApprovalList();
        return Result.Ok(res);
    }

}
