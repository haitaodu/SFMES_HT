package com.smartflow.service;

import java.util.List;
import java.util.Map;

/**
 * @author ：tao
 * @date ：Created in 2020/9/3 17:51
 * @description：${description}
 */

public interface ContainerTypeService {
    /**
     * 托盘类型
     * @return 返回托盘类型下拉列表
     */
    List<Map<String,Object>> getContainerType();
}
