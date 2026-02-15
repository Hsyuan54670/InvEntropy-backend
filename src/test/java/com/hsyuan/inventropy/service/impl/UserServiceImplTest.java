package com.hsyuan.inventropy.service.impl;

import com.hsyuan.inventropy.entity.Project;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * UserServiceImpl 类的单元测试
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private ProjectLogMapper projectLogMapper;

    @Mock
    private FundsLogMapper fundsLogMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        ThreadLocalUtils.set(1001);
    }

    @Test
    void testGetUserInfo() {
        // 准备测试数据
        com.hsyuan.inventropy.entity.User user = new com.hsyuan.inventropy.entity.User();
        user.setId(1001);
        user.setName("张三");
        user.setGender("男");
        user.setAge(25);
        user.setPhone("13800138000");
        user.setCollege("计算机学院");

        when(projectMapper.countIngProjects(anyInt())).thenReturn(3);
        when(projectMapper.countFinishedProjects(anyInt())).thenReturn(5);
        when(userMapper.getUserInfo(anyInt())).thenReturn(user);

        // 执行测试
        Result result = userService.getUserInfo(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());

        UserInformation data = (UserInformation) result.getData();
        assertEquals("张三", data.getName());
        assertEquals(3, data.getIngProjects());
        assertEquals(5, data.getFinishedProjects());
        assertEquals(8, data.getPassedProjects());
    }

    @Test
    void testGetUserIngProjects() {
        // 准备测试数据
        Project project1 = new Project();
        project1.setId(1001L);
        project1.setProjectName("项目A");

        Project project2 = new Project();
        project2.setId(1002L);
        project2.setProjectName("项目B");

        List<Project> projects = Arrays.asList(project1, project2);

        when(projectMapper.getUserIngProjects(anyInt())).thenReturn(projects);

        // 执行测试
        Result result = userService.getUserIngProjects(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
        assertEquals(2, ((List<?>) result.getData()).size());
    }

    @Test
    void testGetUserFinishedProjects() {
        // 准备测试数据
        Project project = new Project();
        project.setId(1001L);
        project.setProjectName("已完成项目");

        List<Project> projects = Arrays.asList(project);

        when(projectMapper.getUserFinishedProjects(anyInt())).thenReturn(projects);

        // 执行测试
        Result result = userService.getUserFinishedProjects(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
    }

    @Test
    void testChangePassword() {
        // 准备测试数据
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1001);
        userDTO.setPassword("newpassword");

        when(userMapper.changePassword(any(UserDTO.class))).thenReturn(true);

        // 执行测试
        Result result = userService.changePassword(userDTO);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).changePassword(any(UserDTO.class));
    }

    @Test
    void testGetTimeLines() {
        // 准备测试数据
        com.hsyuan.inventropy.entity.ProjectLog log = new com.hsyuan.inventropy.entity.ProjectLog();
        log.setId(1);
        log.setProjectId(1001);

        List<com.hsyuan.inventropy.entity.ProjectLog> logs = Arrays.asList(log);

        when(projectLogMapper.getByProjectId(anyInt())).thenReturn(logs);

        // 执行测试
        Result result = userService.getTimeLines(1001);

        // 验证结果
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertNotNull(result.getData());
    }
}
