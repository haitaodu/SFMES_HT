package com.smartflow.common.check;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author ：tao
 * @date ：Created in 2020/11/27 13:58
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public class DateCheckTest {

    @Test
    public void checkDate() throws ParseException {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat
                ("yyyy-mm-dd hh:mm:ss");
        Date begin=simpleDateFormat.parse("2020-11-29 14:00:00");
        Date end=simpleDateFormat.parse("2020-11-27 14:00:00");
        System.out.println(DateCheck.checkDate(begin,end));
    }
}