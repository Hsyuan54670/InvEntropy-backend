package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageTable<T>{
    private Integer page=1;
    private Integer pageSize=5;
    private Long total;
    private List<T> records;
}
