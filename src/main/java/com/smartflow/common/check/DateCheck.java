package com.smartflow.common.check;

import java.util.Date;

/**
 * @author ：tao
 * @date ：Created in 2020/11/27 13:56
 * @description：${description}
 * @modified By：
 * @version: version
 */

public abstract class DateCheck {


    public static boolean checkDate(Date beginTime,Date endTime)
    {
        return beginTime.before(endTime);
    }
}
