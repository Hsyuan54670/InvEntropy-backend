package com.hsyuan.inventropy.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project 实体类的单元测试
 */
class ProjectTest {

    @Test
    void testProjectCreate() {
        LocalDateTime now = LocalDateTime.now();
        Project project = new Project();
        project.setId(1001L);
        project.setProjectName("科研项目A");
        project.setProjectType("科研类");
        project.setApplicant("张三");
        project.setApplicantId(1001);
        project.setFunds(50000.00);
        project.setRemainingFunds(35000.00);
        project.setContent("研究人工智能在医疗领域的应用");
        project.setStartTime(now);
        project.setDeadline(now.plusMonths(6));
        project.setUpdateTime(now);
        project.setStatus(1);
        project.setReason("");

        assertEquals(1001L, project.getId());
        assertEquals("科研项目A", project.getProjectName());
        assertEquals("科研类", project.getProjectType());
        assertEquals("张三", project.getApplicant());
        assertEquals(1001, project.getApplicantId());
        assertEquals(50000.00, project.getFunds());
        assertEquals(35000.00, project.getRemainingFunds());
        assertEquals(1, project.getStatus());
    }

    @Test
    void testProjectWithAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Project project = new Project(1001L, "科研项目A", "科研类", "张三", 1001,
                50000.00, 35000.00, "研究内容", now, now.plusMonths(6),
                now, 1, "");

        assertEquals("科研项目A", project.getProjectName());
        assertEquals(1, project.getStatus());
    }

    @Test
    void testProjectNoArgsConstructor() {
        Project project = new Project();

        assertNull(project.getId());
        assertNull(project.getProjectName());
        assertNull(project.getStatus());
    }

    @Test
    void testProjectStatusValues() {
        Project project = new Project();

        // 测试不同状态
        project.setStatus(0);
        assertEquals(0, project.getStatus());

        project.setStatus(1);
        assertEquals(1, project.getStatus());

        project.setStatus(2);
        assertEquals(2, project.getStatus());

        project.setStatus(3);
        assertEquals(3, project.getStatus());
    }
}
