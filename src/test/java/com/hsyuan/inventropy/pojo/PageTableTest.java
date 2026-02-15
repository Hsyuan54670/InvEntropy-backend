package com.hsyuan.inventropy.pojo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PageTable 类的单元测试
 */
class PageTableTest {

    @Test
    void testPageTableCreate() {
        List<String> records = Arrays.asList("record1", "record2", "record3");
        PageTable<String> pageTable = new PageTable<>();
        pageTable.setPage(1);
        pageTable.setPageSize(10);
        pageTable.setTotal(100L);
        pageTable.setRecords(records);

        assertEquals(1, pageTable.getPage());
        assertEquals(10, pageTable.getPageSize());
        assertEquals(100L, pageTable.getTotal());
        assertEquals(3, pageTable.getRecords().size());
    }

    @Test
    void testPageTableWithAllArgsConstructor() {
        List<String> records = Arrays.asList("a", "b");
        PageTable<String> pageTable = new PageTable<>(2, 20, 50L, records);

        assertEquals(2, pageTable.getPage());
        assertEquals(20, pageTable.getPageSize());
        assertEquals(50L, pageTable.getTotal());
        assertEquals(2, pageTable.getRecords().size());
    }

    @Test
    void testPageTableDefaultValues() {
        PageTable<String> pageTable = new PageTable<>();

        assertEquals(1, pageTable.getPage());
        assertEquals(5, pageTable.getPageSize());
    }

    @Test
    void testPageTableWithEmptyRecords() {
        PageTable<String> pageTable = new PageTable<>(1, 10, 0L, Arrays.asList());

        assertEquals(0, pageTable.getTotal());
        assertTrue(pageTable.getRecords().isEmpty());
    }
}
