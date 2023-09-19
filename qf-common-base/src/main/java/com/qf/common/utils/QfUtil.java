

package com.qf.common.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.MD5;
import com.qf.common.bean.TreeNode;
import com.qf.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 平台自定义工具类集合
 *
 * @author 千锋健哥
 */
@Slf4j
public class QfUtil {
    /**
     * SimpleDateFormat ThreadLocal 保证线程安全
     */
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(CommonConstant.Time.DEFAULT_DATE_FORMAT);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> COMPLETE_DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(CommonConstant.Time.COMPLETE_DATE_FORMAT);
        }
    };

    /**
     * 获取 md5 加密编码
     *
     * @param str String
     * @return String
     */
    public static String md5(String str) {
        MD5 md5 = MD5.create();
        return md5.digestHex(str, StandardCharsets.UTF_8);
    }

    /**
     * 获取 md5 & salt 加密编码
     *
     * @param str  String
     * @param salt String
     * @return String
     */
    public static String md5(String str, String salt) {
        return md5(md5(str) + salt);
    }

    /**
     * 将字符串进行Base64编码
     *
     * @param str String
     * @return Byte Array
     */
    public static String encodeString(String str) {
        return new String(
                Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8
        );
    }

    /**
     * 必须配合encodeString使用，用于encodeString编码之后解码
     *
     * @param str String
     * @return Byte Array
     */
    public static String decodeString(String str) {
        return new String(
                Base64.getDecoder().decode(str),
                StandardCharsets.UTF_8
        );
    }


    /**
     * 将字符串进行Base64编码
     *
     * @param str String
     * @return Byte Array
     */
    public static byte[] encode(String str) {
        return Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将字节流进行Base64编码
     *
     * @param bytes Byte Array
     * @return Byte Array
     */
    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 必须配合encode使用，用于encode编码之后解码
     *
     * @param str String
     * @return Byte Array
     */
    public static byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
    }

    /**
     * 必须配合encode使用，用于encode编码之后解码
     *
     * @param input Byte Array
     * @return Byte Array
     */
    public static byte[] decode(byte[] input) {
        return Base64.getDecoder().decode(input);
    }

    /**
     * 使用 yyyy-MM-dd HH:mm:ss 格式化时间
     *
     * @param date Date
     * @return String
     */
    public static String formatDefaultData(Date date) {
        return DEFAULT_DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }

    /**
     * 将时间字符串 yyyy-MM-dd HH:mm:ss 转为时间类型
     *
     * @param dateString Date String
     * @return Date
     */
    public static Date stringToDefaultDate(String dateString) {
        try {
            return DEFAULT_DATE_FORMAT_THREAD_LOCAL.get().parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 使用 yyyy-MM-dd HH:mm:ss.SSS 格式化时间
     *
     * @param date Date
     * @return String
     */
    public static String formatCompleteData(Date date) {
        return COMPLETE_DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }

    /**
     * 将时间字符串 yyyy-MM-dd HH:mm:ss.SSS 转为时间类型
     *
     * @param dateString Date String
     * @return Date
     */
    public static Date stringToCompleteDate(String dateString) {
        try {
            return COMPLETE_DATE_FORMAT_THREAD_LOCAL.get().parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 按小时推迟时间
     *
     * @param amount Integer
     * @param field  Calendar field : Calendar.HOUR/MINUTE/...
     * @return Date
     */
    public static Date expireTime(int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 判断字符串是否为 用户名格式（2-64）
     *
     * @param name String
     * @return boolean
     */
    public static boolean isName(String name) {
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_]{1,63}$";
        return ReUtil.isMatch(regex, name);
    }

    /**
     * 判断字符串是否为 手机号码格式
     *
     * @param phone String
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        String regex = "^[1]([3-9])[0-9]{9}$";
        return ReUtil.isMatch(regex, phone);
    }

    /**
     * 判断字符串是否为 邮箱地址格式
     *
     * @param mail String
     * @return boolean
     */
    public static boolean isMail(String mail) {
        String regex = "^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$";
        return ReUtil.isMatch(regex, mail);
    }

    /**
     * 判断字符串是否为 密码格式（8-16）
     *
     * @param password String
     * @return boolean
     */
    public static boolean isPassword(String password) {
        String regex = "^[a-zA-Z]\\w{7,15}$";
        return ReUtil.isMatch(regex, password);
    }

    /**
     * 判断字符串是否为 Host格式
     *
     * @param host String
     * @return boolean
     */
    public static boolean isHost(String host) {
        String regex = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
        return ReUtil.isMatch(regex, host);
    }

    /**
     * 判断字符串是否为 驱动端口格式
     *
     * @param port Integer
     * @return boolean
     */
    public static boolean isDriverPort(int port) {
        String regex = "^8[6-7][0-9]{2}$";
        return ReUtil.isMatch(regex, String.valueOf(port));
    }


    /**
     * InputStream 转 String
     * 此方法可以防止中文乱码
     *
     * @param inputStream InputStream
     * @return String
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream boa = new ByteArrayOutputStream();
        try {
            int length = 0;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) > -1) {
                boa.write(buffer, 0, length);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            inputStream.close();
            boa.close();
        }

        byte[] result = boa.toByteArray();
        String temp = new String(result);
        if (temp.contains("gb2312")) {
            return new String(result, "gb2312");
        } else {
            return new String(result, StandardCharsets.UTF_8);
        }
    }

    /**
     * File 转 MultipartFile
     *
     * @param fileInputStream FileInputStream
     * @return MultipartFile
     */
    public static MultipartFile fileInputStreamToMultipartFile(FileInputStream fileInputStream) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        FileItem item = factory.createItem(textFieldName, "text/plain", true, "qfMultipartFile");
        try {
            int length = 0;
            byte[] buffer = new byte[1024];
            OutputStream outputStream = item.getOutputStream();
            while ((length = fileInputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new CommonsMultipartFile(item);
    }

    /**
     * Destroy Process With Command
     *
     * @param process Process
     * @param cmd     Exit Command
     */
    public static void destroyProcessWithCmd(Process process, String cmd) {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        try {
            if (!cmd.equals("")) {
                writer.write(cmd);
                writer.flush();
                writer.close();
            }
            process.destroyForcibly();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return T Array
     */
    public <T extends TreeNode> List<T> buildByLoop(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>(16);
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>(16));
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes 传入的树节点列表
     * @return T Array
     */
    public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>(16);
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes 传入的树节点列表
     * @return T
     */
    public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>(16));
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    /**
     * 获取当前主机的 Local Host
     *
     * @return String
     */
    public static String localHost() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取电脑 Mac 物理地址列表
     *
     * @return Mac Array
     */
    private List<String> localMacList() {
        ArrayList<String> macList = new ArrayList<>(16);
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            StringBuilder stringBuilder = new StringBuilder();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                List<InterfaceAddress> interfaceAddressList = networkInterface.getInterfaceAddresses();
                for (InterfaceAddress interfaceAddress : interfaceAddressList) {
                    InetAddress inetAddress = interfaceAddress.getAddress();
                    NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
                    if (network == null) {
                        continue;
                    }
                    byte[] mac = network.getHardwareAddress();
                    if (mac == null) {
                        continue;
                    }
                    stringBuilder.delete(0, stringBuilder.length());
                    for (int i = 0; i < mac.length; i++) {
                        stringBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    macList.add(stringBuilder.toString());
                }
            }
            if (macList.size() > 0) {
                return macList.stream().distinct().collect(Collectors.toList());
            }
        } catch (Exception ignored) {
        }
        return macList;
    }

    /**
     * Given an address resolve it to as many unique addresses or hostnames as can be found.
     *
     * @param address the address to resolve.
     * @return the addresses and hostnames that were resolved from {@code address}.
     */
    public static Set<String> getHostNames(String address) {
        return getHostNames(address, true);
    }

    /**
     * Given an address resolve it to as many unique addresses or hostnames as can be found.
     *
     * @param address         the address to resolve.
     * @param includeLoopback if {@code true} loopback addresses will be included in the returned set.
     * @return the addresses and hostnames that were resolved from {@code address}.
     */
    public static Set<String> getHostNames(String address, boolean includeLoopback) {
        Set<String> hostNames = new HashSet<>(16);

        try {
            InetAddress inetAddress = InetAddress.getByName(address);

            if (inetAddress.isAnyLocalAddress()) {
                try {
                    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

                    for (NetworkInterface ni : Collections.list(nis)) {
                        Collections.list(ni.getInetAddresses()).forEach(ia -> {
                            if (ia instanceof Inet4Address) {
                                boolean loopback = ia.isLoopbackAddress();

                                if (!loopback || includeLoopback) {
                                    hostNames.add(ia.getHostName());
                                    hostNames.add(ia.getHostAddress());
                                    hostNames.add(ia.getCanonicalHostName());
                                }
                            }
                        });
                    }
                } catch (SocketException e) {
                    log.warn("Failed to NetworkInterfaces for bind address: {}", address, e);
                }
            } else {
                boolean loopback = inetAddress.isLoopbackAddress();

                if (!loopback || includeLoopback) {
                    hostNames.add(inetAddress.getHostName());
                    hostNames.add(inetAddress.getHostAddress());
                    hostNames.add(inetAddress.getCanonicalHostName());
                }
            }
        } catch (UnknownHostException e) {
            log.warn("Failed to get InetAddress for bind address: {}", address, e);
        }

        return hostNames;
    }

    /**
     * 从 Request 中获取指定 Key 的 Header 值
     *
     * @param httpServletRequest HttpServletRequest
     * @param key                String
     * @return String
     */
    public static String getRequestHeader(HttpServletRequest httpServletRequest, String key) {
        return httpServletRequest.getHeader(key);
    }

}
