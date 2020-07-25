package com.smartflow.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author ：tao
 * @date ：Created in 2020/7/9 20:36
 * @description：${description}
 * @modified By：
 * @version: version
 */
@Component
@Aspect
public class WebLogAspect  {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.smartflow.controller.*.*(..))")
    public void webLog() {
        //do nothing as a configMethod
    }

    /**
     * 使用AOP前置通知拦截请求参数信息<br>
     * @param joinPoint 切点
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容 记录 最多半年数据迁移 云备份 nosql 数据库
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name =  enu.nextElement();
            logger.info("name:{},value:{}", name, request.getParameter(name));
        }
        // 传统写在磁盘上有很大缺点： 分布式情况 服务器集群呢？ 20台服务器，
    }

    /**
     * @param ret ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        logger.info("RESPONSE : '{0}'." , ret);
    }

}
