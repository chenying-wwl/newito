package com.qf.common.utils;

/**
 * 指令转换工具类
 * @author 千锋健哥
 */
public class CommandUtils {

    /**
     * char类型传int类型
     * @param c1
     * @param c2
     * @return
     */
    public static int charToInt(char c1, char c2) {
        String num = "0123456789ABCDEF";
        int highBits = num.indexOf(c1);
        int lowBits = num.indexOf(c2);

        return (highBits * 16 + lowBits);
    }

    /**
     * int数组转byte数组
     * @param arrays
     * @return
     */
    public static byte[] intGetBytes(int[] arrays) {
        byte[] bytes = new byte[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            bytes[i] = (byte) (arrays[i] & 0xff);
        }
        return bytes;
    }

    /**
     * byte数组转16进制
     * @param buffer
     * @return
     */
    public static String byteToHex(byte[] buffer) {

        String hs = "";
        String stmp = "";

        for (int n = 0; n < buffer.length; n++) {
            stmp = (Integer.toHexString(buffer[n] & 0XFF));

            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 字符串转16进制
     * @param content
     * @return
     */
    public static byte[] strToHex(String content) {
        content = content.replace(" ", "");                // 去除空格
        content = content.toUpperCase();                   // 全转大写
        char[] chars = content.toCharArray();              // 转换成字符数组
        int length = chars.length / 2;
        // 将字符串先转换成整型数据
        int[] realNums = new int[length];
        for (int i = 0; i < length; i++) {
            realNums[i] = charToInt(chars[2 * i], chars[2 * i + 1]);
            System.out.print(Integer.toHexString(realNums[i]) + " ");  // 打印信息确认是否转换正确
        }
        byte[] sendBytes = intGetBytes(realNums);
        return sendBytes;
    }

}
