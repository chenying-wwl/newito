package com.qf.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 密码加解密
 * @author 千锋健哥
 */
@Slf4j
public class SecuretUtil {

    /**
     * MD5加密之方法三
     * @explain springboot自带MD5加密
     * @param str
     *            待加密字符串
     * @return 16进制加密字符串
     */
    public static String encrypt3ToMD5(String str) {
        String md5 = "";
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param md5  密文
     * @return true/false
     * @author tao
     * @date 2021-6-16 15:32 :56
     */
    public static boolean verify(String text, String md5) {
        //根据传入的密钥进行验证
        String md5Text = encrypt3ToMD5(text);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) throws Exception{
        String md5 = SecuretUtil.encrypt3ToMD5("11111");
        System.out.println("=====" + md5);
        boolean verify = SecuretUtil.verify("123456", md5);
        System.out.println("======"+ verify);
    }
}
