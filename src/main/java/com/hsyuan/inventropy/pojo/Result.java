package com.hsyuan.inventropy.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "统一返回结果")
public class Result {
    @Schema(description = "状态码: 200-成功, 400-失败", example = "200")
    private Integer code;

    @Schema(description = "返回消息",example = "success")
    private String msg;

    @Schema(description = "返回数据")
    private Object data;

    public static Result Ok(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return   result;
    }
    public static Result Ok(){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        return   result;
    }
    public static Result fail(String msg){
        Result result = new Result();
        result.setCode(400);
        result.setMsg(msg);
        return  result;
    }
}
