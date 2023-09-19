package com.qf.common.annotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 事件切片
 *
 * @author : 千锋健哥
 */
@Slf4j
@Aspect
@Component
public class PublishEventAspect {

    @Resource
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.qf.common.annotation.PublishEvent)")
    public void eventCut() {
    }

    /**
     * 在方法返回之后执行事件
     *
     * @param joinPoint
     */
    @AfterReturning("eventCut()")
    public void doAfterReturn(JoinPoint joinPoint) {
        //获取到方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取到当前正在执行的方法
        Method method = methodSignature.getMethod();
        //获取到方法上面的注解
        PublishEvent publishEvent = method.getAnnotation(PublishEvent.class);
        //获取到内部指定的事件类型
        Class eventType = publishEvent.value();
        try {
            //发布指定类型事件
            applicationContext.publishEvent(eventType.newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
