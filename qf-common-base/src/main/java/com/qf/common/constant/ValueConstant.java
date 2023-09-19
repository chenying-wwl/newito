package com.qf.common.constant;

/**
 * 数据相关
 *
 * @author : 千锋健哥
 */
public interface ValueConstant {
    /**
     * 类型相关
     */
    interface Type {
        String HEX = "hex";
        String BYTE = "byte";
        String SHORT = "short";
        String INT = "int";
        String LONG = "long";
        String FLOAT = "float";
        String DOUBLE = "double";
        String BOOLEAN = "boolean";
        String STRING = "string";
    }

    /**
     * 日志常量
     */
    interface LogDictionary {
        //首页
        Integer INDEX = 1;
        //驱动
        Integer DRIVER = 2;
        //驱动属性
        Integer DRIVER_ATTRIBUTE = 3;
        //位号属性
        Integer POINT_ATTRIBUTE = 4;
        //模板管理
        Integer PROFILES = 5;
        //设备管理
        Integer DEVICE = 6;
        //设备驱动
        Integer DEVICE_DRIVER = 7;
        //位号管理
        Integer POINT = 8;
        //位号配置
        Integer POINT_CONF = 9;
        //设备数据
        Integer DEVICE_DATA = 10;
        //传感器操作
        Integer SENSOR = 11;
        //查询
        Integer QUERY = 12;
        //新增
        Integer ADD = 13;
        //修改
        Integer UPDATE = 14;
        //删除
        Integer DELETE = 15;
        //登录
        Integer LOGIN = 16;
        //开启
        Integer OPEN = 17;
        //关闭
        Integer CLOSE = 18;
    }

    /**
     * 租户常量
     */
    interface Tenant {
        //待审核
        Integer CHECK_PENDING = 0;
        //审核通过
        Integer CHECK_YES = 1;
        //审核不通过
        Integer CHECK_NO = 2;

        //启动
        Integer ENABLE_YES = 1;
        //禁用
        Integer ENABLE_NO = 2;
    }

    /**
     * 设备常量
     */
    interface Device {
        //未激活
        Integer ACTIVE_NO = 1;
        //激活
        Integer ACTIVE_YES = 2;

    }
}
