package com.hsyuan.inventropy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkCount {
    Long projectsRecords;
    Long fundsRecords;
    Long projectsOverTime;
}
