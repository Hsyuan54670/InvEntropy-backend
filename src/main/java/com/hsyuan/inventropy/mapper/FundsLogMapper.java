package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.entity.FundsLog;
import com.hsyuan.inventropy.pojo.FundsApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundsLogMapper {
    void insertFundsApply(FundsApplyDTO fundsApply);

    List<FundsApplyDTO> getFundsApprovalList();

    FundsLog getFundsLogById(Integer id);

    void addMarkedById(Integer id, Integer approverId, String comment,Integer status);

    List<FundsLog> getAll();
}
