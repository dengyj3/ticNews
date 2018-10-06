package com.zcgx.ticNews.config.aop.log;

import com.zcgx.ticNews.dao.WebLogDao;
import com.zcgx.ticNews.po.WebLog;
import com.zcgx.ticNews.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    @Autowired
    WebLogDao webLogDao;
    @Pointcut("execution(public * com.zcgx.ticNews.controller.*.updateArticle(..))")
    public void webLog(){}
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        logger.debug("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        WebLog webLog = new WebLog();
        webLog.setClientIp(IpUtils.getIpAddr(request));
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setType(request.getMethod());
        webLog.setUserName("");
        webLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        webLog.setArgs(Arrays.toString(joinPoint.getArgs()));
        webLogDao.saveAndFlush(webLog);
    }
    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){
        logger.debug("WebLogAspect.doAfterReturning()");
        logger.debug("It cost (ms) : " + (System.currentTimeMillis() - startTime.get()));
    }
}
