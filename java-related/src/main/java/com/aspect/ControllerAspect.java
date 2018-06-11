/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ControllerAspect.class);

    /**
     * 针对所有的含有RequestMapping的标签[任意返回值，任意方法，任意参数]
     */
    @Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
    public void controllerPointcut() {}

    @Pointcut("execution(@com.annotation.MyAnnotation * *(..))")
    public void controllerAspect() {}  //实现拦截自定义注解
    
    /**
     * 记录出入日志和接口请求时间
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerPointcut()")
    public Object controllerAround(ProceedingJoinPoint joinPoint)
            throws Throwable {
        // 执行的类
        String exeType = null;
        // 执行的方法
        String exeMethod = null;
        long start = System.currentTimeMillis();
        try {
            // 获取接口的请求参数
            MethodSignature signature = (MethodSignature) joinPoint
                    .getSignature();
            exeType = signature.getDeclaringType().getCanonicalName();
            exeMethod = signature.getMethod().getName();

            LOG.info("[{}.{} start]", new Object[] {
                exeType, exeMethod
            });
            System.out.println(exeType + " " + exeMethod);
            return joinPoint.proceed();

        } catch (Throwable ex) {

        } finally {
            LOG.info("[{}.{} end,cost {} ms]", new Object[] {
                exeType, exeMethod, (System.currentTimeMillis() - start)
            });
        }
        return joinPoint.proceed();
    }
}
