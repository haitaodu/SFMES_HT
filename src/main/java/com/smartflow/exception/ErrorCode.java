package com.smartflow.exception;

/**
 * @author ：tao
 * @date ：Created in 2020/7/26 19:19
 * @description：错误码枚举
 */
public interface ErrorCode {
    /**
     * 获取错误码
     * @return 返回错误码Code
     */
    String getCode();

    /**
     * 获取错误信息
     * @return 返回错误码描述
     */
    String getDescription();

}
