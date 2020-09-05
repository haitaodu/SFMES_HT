package com.smartflow.util.global;

/**
 * @author ：tao
 * @date ：Created in 2020/4/18 22:11
 */

 public  class PageUtil {
     private PageUtil(){}


    public static String paseState(int state)
    {
     switch (state) {
         case  1:return "已激活";
         case  0:return "未激活";
         default:return  "已删除";
     }
    }
    /**
     * 将boolean类型的转化为“是”或者“否”
     * @param arg boolen参数
     * @return 返回是或者否
     */
    public static String  parseToTrueFalse(Boolean arg)
    {
        if (Boolean.TRUE.equals(arg))
        {
            return "是";
        }
        else {
            return "否";
        }
    }

}
