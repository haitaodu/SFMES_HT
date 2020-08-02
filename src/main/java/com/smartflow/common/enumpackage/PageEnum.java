package com.smartflow.common.enumpackage;

/**
 * @author ：tao
 * @date ：Created in 2020/8/1 22:00
 * @description：${description}
 */

public enum  PageEnum {
    /**
     * 分页大小
     * 分页页码
     */
    PAGESIZE("PageSize"),
    PAGEINDEX("PageIndex");
    private final String value;
    PageEnum(String value)
    {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
 }
