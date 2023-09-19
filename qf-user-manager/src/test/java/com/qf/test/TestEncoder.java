package com.qf.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 千锋健哥
 */
public class TestEncoder {

    @Test
    public void test01() {
        // 创建密码解析器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 对密码进行加密
        String pwd = bCryptPasswordEncoder.encode("qf");
        // 打印加密之后的数据
        System.out.println("加密之后数据：" + pwd);
        //判断原字符加密后和加密之前是否匹配
        boolean result = bCryptPasswordEncoder.matches("qf", pwd); 	// 打印比较结果
        System.out.println("比较结果："+result);
    }
}
