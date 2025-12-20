package com.hsyuan.inventropy.mapper;

import com.hsyuan.inventropy.pojo.FundsApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundsLogMapper {
    void insertFundsApply(FundsApplyDTO fundsApply);

    List<FundsApplyDTO> getFundsApprovalList();
}
