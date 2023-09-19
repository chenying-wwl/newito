package com.qf.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 千锋健哥
 */
@Setter
@Getter
public class ThreadProperty {
    private String prefix;
    private int corePoolSize;
    private int maximumPoolSize;
    private int keepAliveTime;
}
