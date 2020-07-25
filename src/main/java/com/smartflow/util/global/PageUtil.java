package com.smartflow.util.global;

/**
 * @author ：tao
 * @date ：Created in 2020/4/18 22:11
 * @description：公用分页类
 * @modified By：
 * @version: version
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

}
