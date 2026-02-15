package com.hsyuan.inventropy.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JwtUtils 工具类的单元测试
 */
class JwtUtilsTest {

    @Test
    void testGenerateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "testuser");
        claims.put("userType", "admin");

        String token = JwtUtils.generateToken(claims);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.split("\\.").length == 3); // JWT 格式: header.payload.signature
    }

    @Test
    void testParseToken() throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1001);
        claims.put("username", "zhangsan");
        claims.put("userType", "user");

        String token = JwtUtils.generateToken(claims);
        Map<String, Object> parsedClaims = JwtUtils.parseToken(token);

        assertNotNull(parsedClaims);
        assertEquals(1001, parsedClaims.get("id"));
        assertEquals("zhangsan", parsedClaims.get("username"));
        assertEquals("user", parsedClaims.get("userType"));
    }

    @Test
    void testGenerateAndParseTokenConsistency() throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 999);
        claims.put("username", "admin");
        claims.put("userType", "admin");

        String token = JwtUtils.generateToken(claims);
        Map<String, Object> parsed = JwtUtils.parseToken(token);

        assertEquals(claims.get("id"), parsed.get("id"));
        assertEquals(claims.get("username"), parsed.get("username"));
        assertEquals(claims.get("userType"), parsed.get("userType"));
    }

    @Test
    void testParseTokenWithInvalidToken() {
        assertThrows(Exception.class, () -> {
            JwtUtils.parseToken("invalid.token.here");
        });
    }

    @Test
    void testGenerateTokenWithEmptyClaims() {
        Map<String, Object> claims = new HashMap<>();

        String token = JwtUtils.generateToken(claims);

        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3);
    }
}
