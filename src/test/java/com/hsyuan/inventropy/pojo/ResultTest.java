package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Result 类的单元测试
 */
class ResultTest {

    @Test
    void testOkWithData() {
        Map<String, Object> data = new HashMap<>();
        data.put("key", "value");

        Result result = Result.Ok(data);

        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals(data, result.getData());
    }

    @Test
    void testOkWithoutData() {
        Result result = Result.Ok();

        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testFail() {
        String errorMessage = "用户名或密码错误";

        Result result = Result.fail(errorMessage);

        assertNotNull(result);
        assertEquals(400, result.getCode());
        assertEquals(errorMessage, result.getMsg());
        assertNull(result.getData());
    }

    @Test
    void testFailWithEmptyMessage() {
        Result result = Result.fail("");

        assertNotNull(result);
        assertEquals(400, result.getCode());
        assertEquals("", result.getMsg());
    }

    @Test
    void testOkWithNullData() {
        Result result = Result.Ok(null);

        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertNull(result.getData());
    }
}
