package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDTO 类的单元测试
 */
class UserDTOTest {

    @Test
    void testUserDTOCreate() {
        UserDTO user = new UserDTO();
        user.setId(1001);
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setUserType("user");

        assertEquals(1001, user.getId());
        assertEquals("zhangsan", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("user", user.getUserType());
    }

    @Test
    void testUserDTOWithAllArgsConstructor() {
        UserDTO user = new UserDTO(1001, "zhangsan", "123456", "admin");

        assertEquals(1001, user.getId());
        assertEquals("zhangsan", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("admin", user.getUserType());
    }

    @Test
    void testUserDTONoArgsConstructor() {
        UserDTO user = new UserDTO();

        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getUserType());
    }

    @Test
    void testUserDTOSetters() {
        UserDTO user = new UserDTO();
        user.setId(1002);
        user.setUsername("lisi");
        user.setPassword("password");
        user.setUserType("user");

        assertNotNull(user);
        assertEquals(1002, user.getId());
        assertEquals("lisi", user.getUsername());
    }
}
