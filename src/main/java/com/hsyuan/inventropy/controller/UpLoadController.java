package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    public Result uploadContent(MultipartFile  file) throws Exception {
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.Ok(url);
    }
}
