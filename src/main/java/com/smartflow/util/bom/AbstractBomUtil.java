package com.smartflow.util.bom;

/**
 * @author ：tao
 * @date ：Created in 2020/7/15 19:58
 * @description：${description}
 * @modified By：
 * @version: version
 */

public abstract class AbstractBomUtil {
    public static String parseBoolenToString(boolean arg)
    {
        if (arg)
        {
            return "是";
        }
        else {
            return "否";
        }
    }
}
