package com.hsyuan.inventropy.pojo;

import lombok.Data;

@Data
public class Result {
    //code,msg,data
    private Integer code;
    private String msg;
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