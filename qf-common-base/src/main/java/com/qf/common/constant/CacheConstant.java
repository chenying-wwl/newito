package com.qf.common.constant;

/**
 * 缓存相关
 *
 * @author : 千锋健哥
 */
public interface CacheConstant {
    /**
     * 超时相关
     */
    interface Timeout {
        /**
         * salt 在 redis 中的失效时间，分钟
         */
        int SALT_CACHE_TIMEOUT = 5;
        /**
         * user 登陆限制失效时间，分钟
         */
        int USER_LIMIT_TIMEOUT = 5;
        /**
         * token 在 redis 中的失效时间，小时
         */
        int TOKEN_CACHE_TIMEOUT = 12;
    }

    /**
     * 实体相关
     */
    interface Entity {
        String TENANT = "tenant";
        String TENANT_BIND = "tenant_bind";
        String BLACK_IP = "black_ip";
        String RTMP = "rtmp";
        String USER = "user";
        String DRIVER = "driver";
        String DRIVER_INFO = "driver_info";
        String DRIVER_ATTRIBUTE = "driver_attribute";
        String PROFILE = "profile";
        String PROFILE_BIND = "profile_bind";
        String GROUP = "group";
        String DEVICE = "device";
        String POINT = "point";
        String POINT_ATTRIBUTE = "point_attribute";
        String POINT_INFO = "point_info";
        String LABEL = "label";
        String LABEL_BIND = "label_bind";
    }

    /**
     * 前缀相关
     */
    interface Prefix {
        String REAL_TIME_VALUE_KEY_PREFIX = Entity.DEVICE + Suffix.VALUE + CommonConstant.Symbol.SEPARATOR;
        String DEVICE_STATUS_KEY_PREFIX = Entity.DEVICE + Suffix.STATUS + CommonConstant.Symbol.SEPARATOR;
    }

    /**
     * 后缀相关
     */
    interface Suffix {
        String ID = "_id";
        String TENANT_ID = "_tenant_id";
        String USER_ID = "_user_id";
        String DRIVER_ID = "_driver_id";
        String GROUP_ID = "_group_id";
        String PROFILE_ID = "_profile_id";
        String POINT_ID = "_point_id";
        String DEVICE_ID = "_device_id";
        String ATTRIBUTE_ID = "_attribute_id";
        String ENTITY_ID = "_entity_id";

        String NAME = "_name";
        String PHONE = "_phone";
        String EMAIL = "_email";
        String SALT = "_salt";
        String TOKEN = "_token";
        String LIMIT = "_limit";
        String VALUE = "_value";
        String PING = "_Ping";
        String CMD = "_cmd";
        String STATUS = "_status";
        String TYPE = "_type";
        String UNIT = "_unit";
        String IP = "_ip";
        String HOST_PORT = "_host_port";
        String SERVICE_NAME = "_service_name";

        String LIST = "_list";
        String DIC = "_dic";
    }

}
