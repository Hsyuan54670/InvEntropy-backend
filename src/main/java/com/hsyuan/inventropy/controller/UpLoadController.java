package com.hsyuan.inventropy.controller;

import com.hsyuan.inventropy.pojo.Result;
import com.hsyuan.inventropy.utils.AliyunOSSOperator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Tag(name = "文件上传模块", description = "上传文件并返回可访问URL")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功", content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(responseCode = "400", description = "请求失败", content = @Content(schema = @Schema(implementation = Result.class)))
})
public class UpLoadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    @Operation(summary = "上传文件", description = "上传文件到对象存储并返回文件URL")
    public Result uploadContent(
            @Parameter(description = "上传文件", required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary")))
            MultipartFile  file) throws Exception {
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.Ok(url);
    }
}
