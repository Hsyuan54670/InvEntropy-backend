package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginInfo 类的单元测试
 */
class LoginInfoTest {

    @Test
    void testLoginInfoCreate() {
        LoginInfo info = new LoginInfo();
        info.setUserType("admin");
        info.setToken("eyJhbGciOiJIUzI1NiJ9...");

        assertEquals("admin", info.getUserType());
        assertEquals("eyJhbGciOiJIUzI1NiJ9...", info.getToken());
    }

    @Test
    void testLoginInfoWithAllArgsConstructor() {
        LoginInfo info = new LoginInfo("user", "token123456");

        assertEquals("user", info.getUserType());
        assertEquals("token123456", info.getToken());
    }

    @Test
    void testLoginInfoNoArgsConstructor() {
        LoginInfo info = new LoginInfo();

        assertNull(info.getUserType());
        assertNull(info.getToken());
    }

    @Test
    void testLoginInfoUserTypes() {
        LoginInfo info = new LoginInfo();

        info.setUserType("admin");
        assertEquals("admin", info.getUserType());

        info.setUserType("user");
        assertEquals("user", info.getUserType());
    }
}
