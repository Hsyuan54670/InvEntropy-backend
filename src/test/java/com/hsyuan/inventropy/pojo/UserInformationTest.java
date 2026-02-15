package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserInformation 类的单元测试
 */
class UserInformationTest {

    @Test
    void testUserInformationCreate() {
        UserInformation info = new UserInformation();
        info.setId(1001);
        info.setName("张三");
        info.setGender("男");
        info.setAge(25);
        info.setPhone("13800138000");
        info.setCollege("计算机学院");
        info.setPassedProjects(10);
        info.setFinishedProjects(5);
        info.setIngProjects(3);

        assertEquals(1001, info.getId());
        assertEquals("张三", info.getName());
        assertEquals("男", info.getGender());
        assertEquals(25, info.getAge());
        assertEquals("13800138000", info.getPhone());
        assertEquals("计算机学院", info.getCollege());
        assertEquals(10, info.getPassedProjects());
        assertEquals(5, info.getFinishedProjects());
        assertEquals(3, info.getIngProjects());
    }

    @Test
    void testUserInformationWithAllArgsConstructor() {
        UserInformation info = new UserInformation(1001, "李四", "女", 24,
                "13900139000", "数学学院", 8, 4, 2);

        assertEquals("李四", info.getName());
        assertEquals("女", info.getGender());
        assertEquals(24, info.getAge());
    }

    @Test
    void testUserInformationNoArgsConstructor() {
        UserInformation info = new UserInformation();

        assertNull(info.getId());
        assertNull(info.getName());
        assertNull(info.getGender());
    }
}
