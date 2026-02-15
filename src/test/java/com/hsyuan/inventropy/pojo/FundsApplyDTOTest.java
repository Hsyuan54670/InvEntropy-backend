package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FundsApplyDTO 类的单元测试
 */
class FundsApplyDTOTest {

    @Test
    void testFundsApplyDTOCreate() {
        LocalDateTime now = LocalDateTime.now();
        FundsApplyDTO dto = new FundsApplyDTO();
        dto.setId(2001);
        dto.setProjectId(1001);
        dto.setApplicant("张三");
        dto.setReason("购买实验材料");
        dto.setAppliedFunds(5000.00);
        dto.setRemainingFunds(15000.00);
        dto.setApplicantId(1001);
        dto.setAppliedTime(now);

        assertEquals(2001, dto.getId());
        assertEquals(1001, dto.getProjectId());
        assertEquals("张三", dto.getApplicant());
        assertEquals("购买实验材料", dto.getReason());
        assertEquals(5000.00, dto.getAppliedFunds());
        assertEquals(15000.00, dto.getRemainingFunds());
        assertEquals(1001, dto.getApplicantId());
        assertEquals(now, dto.getAppliedTime());
    }

    @Test
    void testFundsApplyDTOWithAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        FundsApplyDTO dto = new FundsApplyDTO(2001, 1001, "张三", "购买实验材料",
                5000.00, 15000.00, 1001, now);

        assertEquals(2001, dto.getId());
        assertEquals(1001, dto.getProjectId());
        assertEquals("张三", dto.getApplicant());
    }

    @Test
    void testFundsApplyDTONoArgsConstructor() {
        FundsApplyDTO dto = new FundsApplyDTO();

        assertNull(dto.getId());
        assertNull(dto.getProjectId());
        assertNull(dto.getApplicant());
    }
}
