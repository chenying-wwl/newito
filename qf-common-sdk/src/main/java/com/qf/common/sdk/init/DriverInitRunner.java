package com.qf.common.sdk.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 初始化SDK, 元数据初始化
 * @author 千锋健哥
 */
@Component
@ComponentScan(basePackages = {
        "com.qf.common.sdk"
})
public class DriverInitRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=============驱动SDK初始化===============");
    }

}
