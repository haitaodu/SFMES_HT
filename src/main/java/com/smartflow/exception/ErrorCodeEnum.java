package com.smartflow.exception;

import org.apache.poi.util.StringUtil;

/**
 * @author ：tao
 * @date ：Created in 2020/7/26 19:22
 * @description：${description}
 */

public enum  ErrorCodeEnum  implements ErrorCode{

    /**
     *  未指明的异常
     */
    UNSPECIFIED("500",
            "网络异常，请稍后再试"),
    NO_SERVICE("404",
            "网络异常, 服务器熔断"),

    /**
     * 通用异常类
     */
    REQUEST_ERROR("400",
            "入参异常,请检查入参后再次调用"),
    PAGE_ERROR("4001",
            "分页参数错误"),
    ID_IS_NULL("4003",
            "ID不能为空"),
    SEARCH_IS_NULL("4004",
            "搜索条件不能为空"),;

    /**
     *  错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String description;

    /**
     * @param code 错误码
     * @param description 描述
     */
    ErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }


    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static ErrorCodeEnum getByCode(String code) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code){
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
