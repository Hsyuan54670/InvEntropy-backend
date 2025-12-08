package com.hsyuan.inventropy.utils;

public class ThreadLocalUtils {
    private static final ThreadLocal<Object> CURRENT_LOCAL=new ThreadLocal<>();
    public static void set(Object empId){
        CURRENT_LOCAL.set(empId);
    }
    public static Object get(){
        return CURRENT_LOCAL.get();
    }
    public static void remove(){
        CURRENT_LOCAL.remove();
    }
}
