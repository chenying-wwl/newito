package com.qf.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Aes/Rsa 加密密钥
 *
 * @author 千锋健哥
 */
public class Keys {

    /**
     * Aes 密钥
     */
    @Data
    @AllArgsConstructor
    public static class Aes {
        private String privateKey;
    }

    /**
     * RSA 密钥对
     */
    @Data
    @AllArgsConstructor
    public static class Rsa {
        private String publicKey;
        private String privateKey;
    }
}