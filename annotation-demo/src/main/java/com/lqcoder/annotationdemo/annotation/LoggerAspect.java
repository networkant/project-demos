package com.lqcoder.annotationdemo.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：luomengsun.
 * @Date ：Created in 16:10 2019/12/15
 * @Description：
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {
 private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(com.lqcoder.annotationdemo.annotation.OutputLog)")
    public void weblog(){

    }

    @Around("weblog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        List<Object> logArgs = Arrays.stream(point.getArgs())
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        try {
            logger.info("请求url={}, 请求参数={}", request.getRequestURI(), JSON.toJSONString(logArgs));
        } catch (Exception e) {
            logger.error("请求参数获取异常", e);
        }
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        try {
            logger.info("请求耗时={}ms, 返回结果={}", time, JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("返回参数获取异常", e);
        }
        return result;
    }

}
