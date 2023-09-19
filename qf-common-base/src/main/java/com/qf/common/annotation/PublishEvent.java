package com.qf.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 发布事件注解,AOP用,需要发布简单事件的方法可以通过当前注解进行标识
 *
 * @author : 千锋健哥
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvent {
    /**
     * 需要发布的事件类型
     * @return
     */
    Class value();
}
