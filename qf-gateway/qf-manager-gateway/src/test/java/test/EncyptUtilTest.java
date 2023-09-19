package test;

import com.qf.gateway.utils.EncryptUtil;

/**
 * @author 千锋健哥
 */
public class EncyptUtilTest {

    public static void main(String[] args) {
        String deviceKey = "1648954167023718400";
        String deviceSecuret = "4e925941edce4252a1908b630733b80b";
        String encrypt = EncryptUtil.encrypt(deviceKey + deviceSecuret);

        //boolean valid = EncryptUtil.valid("1235", encrypt);
        System.out.println("=========" + encrypt);

        String test = "d8bfd9680e4cc36c8c4d0b550d7de928327101a79c81074467218f3781fc055d52767480";
        boolean valid = EncryptUtil.valid(deviceKey + deviceSecuret, test);
        System.out.println("============" + valid);
    }
}
