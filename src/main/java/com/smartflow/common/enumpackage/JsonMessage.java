package com.smartflow.common.enumpackage;

/**
 * @author ：tao
 * @date ：Created in 2020/8/1 22:15
 * @description：${description}
 */
public enum JsonMessage {
    /**
     *
     */
    FALL_INSERT("插入数据失败",0),
    FALL_UPDATE("更新数据失败",0),
    FALL_DEL("删除数据失败",0),
    FALL_READ("读取数据失败",0),
    SUCCESS_DEL("删除数据成功",200),
    SUCCESS_UPDATE("更改数据成功",200),
    SUCCESS_INSERT("添加数据成功",200),
    SUCCESS_READ("读取数据成功",200);
    private final String message;
    private final  int code;
    JsonMessage(String message,int code) {
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }}
