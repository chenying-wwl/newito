package com.qf.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 千锋健哥
 */
public class TestBCryptPasswordEncoder {

    @Test
    public void testEndoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String qf = bCryptPasswordEncoder.encode("qf");
        System.out.println("====" + qf);
    }

}
