package com.smartflow.common.enumpackage;

/**
 * @author ：tao
 * @date ：Created in 2020/8/1 22:51
 * @description：${description}
 */
public enum StationEnum {
    /**
     *
     */
    STATION_PRODUCTION(1,"生产工站"),
    STATION_TEST(2,"测试工站"),
    STATION_FIX(3,"维修工站"),
    STATION_VIRTUAL(4,"虚拟工站"),
    STATION_MATERIAL(5,"上料工站"),
    STATION_CRASH(6,"清洗工站"),
    STATION_PAK(7,"配送工站");

    private final int key;
    private final String value;

    StationEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
