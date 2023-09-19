package com.qf.common.annotation;

import java.lang.annotation.*;

/**
 * 日志切点
 *
 * @author : 千锋健哥
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logs {
    String value() default "";

    LogsType type() default LogsType.INFO;

    String tag() default "";

    boolean save() default false;
}