package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.entity.FundsLog;
import com.hsyuan.inventropy.entity.Project;
import com.hsyuan.inventropy.entity.ProjectLog;
import com.hsyuan.inventropy.mapper.FundsLogMapper;
import com.hsyuan.inventropy.mapper.ProjectLogMapper;
import com.hsyuan.inventropy.mapper.ProjectMapper;
import com.hsyuan.inventropy.mapper.UserMapper;
import com.hsyuan.inventropy.pojo.*;
import com.hsyuan.inventropy.utils.ThreadLocalUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * AdminServiceImpl 类的单元测试
 */
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private ProjectLogMapper projectLogMapper;

    @Mock
    private FundsLogMapper fundsLogMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        ThreadLocalUtils.set(1001);
    }

    @Test
    void testApproveProject() {
        // 执行测试
        Result result = adminService.approveProject(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(projectMapper, times(1)).updateProjectStatus(eq(1001), eq(3));
    }

    @Test
    void testNotApproveProject() {
        String reason = "材料不完整";

        // 执行测试
        Result result = adminService.notApproveProject(1001, reason);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(projectMapper, times(1)).updateProjectStatus(eq(1001), eq(1));
        verify(projectMapper, times(1)).updateReason(eq(1001), eq(reason));
    }

    @Test
    void testApproveFundsSuccess() {
        // 准备测试数据 - 使用 FundsLog 而非 FundsApplyDTO
        FundsLog fundsLog = new FundsLog();
        fundsLog.setId(2001);
        fundsLog.setProjectId(1001);

        Project project = new Project();
        project.setId(1001L);
        project.setRemainingFunds(10000.00);

        when(fundsLogMapper.getFundsLogById(anyInt())).thenReturn(fundsLog);
        when(projectMapper.getProjectById(anyInt())).thenReturn(project);

        // 执行测试
        Result result = adminService.approveFunds(2001, "同意", 5000.00);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(fundsLogMapper, times(1)).addMarkedById(anyInt(), anyInt(), anyString(), eq(2));
    }

    @Test
    void testApproveFundsExceedLimit() {
        // 准备测试数据 - 使用 FundsLog 而非 FundsApplyDTO
        FundsLog fundsLog = new FundsLog();
        fundsLog.setId(2001);
        fundsLog.setProjectId(1001);

        Project project = new Project();
        project.setId(1001L);
        project.setRemainingFunds(3000.00); // 小于申请的金额

        when(fundsLogMapper.getFundsLogById(anyInt())).thenReturn(fundsLog);
        when(projectMapper.getProjectById(anyInt())).thenReturn(project);

        // 执行测试
        Result result = adminService.approveFunds(2001, "同意", 5000.00);

        // 验证结果
        assertNotNull(result);
        assertEquals(400, result.getCode());
        assertEquals("申请资金超出剩余资金", result.getMsg());
    }

    @Test
    void testNotApproveFunds() {
        String comment = "预算不合理";

        // 执行测试
        Result result = adminService.notApproveFunds(2001, comment);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(fundsLogMapper, times(1)).addMarkedById(eq(2001), anyInt(), eq(comment), eq(1));
    }

    @Test
    void testDeleteProject() {
        // 执行测试
        Result result = adminService.deleteProject(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(fundsLogMapper, times(1)).deleteByProjectId(eq(1001));
        verify(projectLogMapper, times(1)).deleteByProjectId(eq(1001));
        verify(projectMapper, times(1)).deleteProjectById(eq(1001));
    }

    @Test
    void testUpdateDeadline() {
        LocalDateTime newDeadline = LocalDateTime.of(2026, 12, 31, 23, 59, 59);

        // 执行测试
        Result result = adminService.updateDeadline(1001, newDeadline, 2);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(projectMapper, times(1)).updateProjectStatus(eq(1001), eq(2));
        verify(projectMapper, times(1)).updateDeadline(eq(1001), eq(newDeadline));
    }

    @Test
    void testGetAccountList() {
        // 准备测试数据
        UserAccountForAdmin account = new UserAccountForAdmin();
        account.setId(1001);
        account.setUsername("testuser");

        List<UserAccountForAdmin> accounts = Arrays.asList(account);
        when(userMapper.getAccountForAdmin()).thenReturn(accounts);

        // 执行测试
        Result result = adminService.getAccountList();

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
    }

    @Test
    void testAddAccountWhenAlreadyExists() {
        // 准备测试数据
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("phone", "13800138000");
        params.put("college", "计算机学院");
        params.put("gender", "男");
        params.put("age", 25);

        when(userMapper.isExitsByUsername(anyString())).thenReturn(true);

        // 执行测试
        Result result = adminService.addAccount(params);

        // 验证结果
        assertNotNull(result);
        assertEquals(400, result.getCode());
        assertEquals("该账户已存在", result.getMsg());
    }
}
