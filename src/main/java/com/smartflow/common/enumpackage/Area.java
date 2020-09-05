package com.smartflow.common.enumpackage;

/**
 * @author ：tao
 * @date ：Created in 2020/8/1 22:10
 * @description：${description}
 */
public enum Area {
    /**
     *
     */
    AREA_NUMBER("AreaNumber"),
    AREA_NAME("AreaName");
    private String value;

    Area(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
