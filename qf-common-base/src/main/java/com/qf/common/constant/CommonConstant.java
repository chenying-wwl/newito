package com.qf.common.constant;

/**
 * 千锋平台常量
 *
 * @author : 千锋健哥
 */
public interface CommonConstant {

    /**
     * 符号相关
     */
    interface Symbol {
        /**
         * 点
         */
        String DOT = ".";

        /**
         * 下划线
         */
        String UNDERSCORE = "_";

        /**
         * 星号
         */
        String ASTERISK = "*";

        /**
         * 井号
         */
        String HASHTAG = "#";

        /**
         * 分隔符
         */
        String SEPARATOR = "::";

        /**
         * 斜线
         */
        String SLASH = "/";
    }

    /**
     * 算法相关
     */
    interface Algorithm {
        /**
         * 默认密钥
         */
        String DEFAULT_KEY = "com.qf";

        /**
         * 默认密码
         */
        String DEFAULT_PASSWORD = "qfqfqf";

        /**
         * 加密算法 对称AES
         */
        String ALGORITHM_AES = "AES";

        /**
         * 加密算法 非对称RSA
         */
        String ALGORITHM_RSA = "RSA";

        /**
         * 加密算法 SHA256withRSA
         */
        String ALGORITHM_SHA256_RSA = "SHA256withRSA";

        /**
         * 证书类型 X.509
         */
        String CERTIFICATE_X509 = "X.509";

        /**
         * 证书类型 PKCS12
         */
        String CERTIFICATE_PKCS12 = "PKCS12";

        /**
         * 证书类型 JKS
         */
        String CERTIFICATE_JKS = "jks";
    }

    /**
     * 时间相关
     */
    interface Time {
        /**
         * 时区
         */
        String TIMEZONE = "GMT+8";

        /**
         * 时间格式化
         */
        String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String COMPLETE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    }

    /**
     * 文件夹相关
     */
    interface Folder {
        /**
         * 用户主目录
         */
        String USER_HOME_PATH = System.getProperty("user.home") + "/.qf/";

        /**
         * 默认上传文件的缓存位置
         */
        String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + "/qf/";
    }

    /**
     * 响应相关
     */
    interface Response {
        String OK = "ok";
        String ERROR = "error";
    }

    /**
     * 消息相关
     */
    interface Rabbit {
        // Arguments
        String MESSAGE_TTL = "x-message-ttl";

        // Event
//        String TOPIC_EXCHANGE_EVENT = "qf.exchange.event";
//        String ROUTING_DRIVER_EVENT_PREFIX = "qf.routing.event.driver.";
//        String QUEUE_DRIVER_EVENT = "qf.queue.event.driver";
//        String ROUTING_DEVICE_EVENT_PREFIX = "qf.routing.event.device.";
//        String QUEUE_DEVICE_EVENT = "qf.queue.event.device";

        // Metadata
//        String TOPIC_EXCHANGE_METADATA = "qf.exchange.metadata";
//        String ROUTING_DRIVER_METADATA_PREFIX = "qf.routing.metadata.driver.";
//        String QUEUE_DRIVER_METADATA_PREFIX = "qf.queue.metadata.driver.";

        // Value
        String TOPIC_EXCHANGE_VALUE = "qf.exchange.value";
        String ROUTING_DEVICE_VALUE_PREFIX = "qf.routing.value.device.";
        String QUEUE_DEVICE_VALUE = "qf.queue.value.device";
        String ROUTING_DEVICE_CMD_PREFIX = "qf.routing.value.cmd.";
        String QUEUE_DEVICE_CMD = "qf.queue.value.cmd";


    }

    /**
     * 存储相关
     */
    interface Storage {
        /**
         * 设备数据存储集合前缀
         */
//        String POINT_VALUE_PREFIX = CacheConstant.Entity.POINT +
//                CacheConstant.Suffix.VALUE + Symbol.UNDERSCORE;
    }

    /**
     * 状态相关
     */
    interface Status {
        /**
         * 注册状态相关
         */
        String REGISTERING = "REGISTERING";
        String UNREGISTERED = "UNREGISTERED";

        /**
         * 运行状态相关
         */
        String ONLINE = "ONLINE";
        String OFFLINE = "OFFLINE";
        String MAINTAIN = "MAINTAIN";
        String FAULT = "FAULT";
    }

    /**
     * 驱动相关
     */
    interface Driver {
        int DEFAULT_MAX_REQUEST_SIZE = 100;

        /**
         * 事件相关
         */
        interface Event {
            /**
             * 驱动注册握手事件，该事件用于校验当前 qf-center-manager 是否可用
             */
            String DRIVER_HANDSHAKE = "driver_handshake";
            String DRIVER_HANDSHAKE_BACK = "driver_handshake_back";

            /**
             * 驱动注册事件，该事件用于向 qf-center-manager 注册驱动配置信息
             */
            String DRIVER_REGISTER = "driver_register";
            String DRIVER_REGISTER_BACK = "driver_register_back";

            /**
             * 同步驱动元数据时间，该事件用于向 qf-center-manager 发送驱动元数据同步请求
             */
            String DRIVER_METADATA_SYNC = "driver_metadata_sync";
            String DRIVER_METADATA_SYNC_BACK = "driver_metadata_sync_back";

            /**
             * 驱动心跳事件，该事件用于向 qf-center-manager 发送驱动的当前状态
             */
            String DRIVER_HEARTBEAT = "driver_heartbeat";

            String ERROR = "error";
        }

        /**
         * 类型
         */
        interface Type {
            String DRIVER = "driver";
            String GATEWAY = "gateway";
            String PROFILE = "profile";
            String DEVICE = "device";
            String POINT = "point";
            String DRIVER_INFO = "driver_info";
            String POINT_INFO = "point_info";
        }

        interface Profile {
            String ADD = "add_profile";
            String DELETE = "delete_profile";
            String UPDATE = "update_profile";
        }

        interface Device {
            String ADD = "add_device";
            String DELETE = "delete_device";
            String UPDATE = "update_device";
        }

        interface Point {
            String ADD = "add_point";
            String DELETE = "delete_point";
            String UPDATE = "update_point";
        }

        interface DriverInfo {
            String ADD = "add_driver_info";
            String DELETE = "delete_driver_info";
            String UPDATE = "update_driver_info";
        }

        interface PointInfo {
            String ADD = "add_point_info";
            String DELETE = "delete_point_info";
            String UPDATE = "update_point_info";
        }
    }

    /**
     * 设备相关
     */
    interface Device {

        /**
         * 设备事件
         */
        interface Event {
            /**
             * 设备心跳事件
             */
            String HEARTBEAT = "heartbeat";

            /**
             * 超出上限事件
             */
            String OVER_UPPER_LIMIT = "over_upper_limit";

            /**
             * 超出下限事件
             */
            String OVER_LOWER_LIMIT = "over_lower_limit";

            /**
             * 用于记录错误事件类型
             */
            String ERROR = "error";
        }
    }

    /**
     * 策略工厂相关
     */
    interface RepositoryStrategy {
        String REPOSITORY_STRATEGY = "repository" + Symbol.SEPARATOR;

        String REPOSITORY_STRATEGY_REDIS = "redis";
        String REPOSITORY_STRATEGY_MONGO = "mongo";
        String REPOSITORY_STRATEGY_INFLUXDB = "influxdb";
        String REPOSITORY_STRATEGY_OPENTSDB = "opentsdb";
        String REPOSITORY_STRATEGY_ELASTICSEARCH = "elasticsearch";
    }

    /**
     * 继电器指令
     */
    interface RelayCommand {
        String RELAY_OPEN = "A\r";
        String RELAY_CLOSE = "a\r";
    }

    /**
     * 协议
     */
    interface Protocol {
        String HTTP = "http";
        String MQTT = "mqtt";
        String TCP = "tcp";
    }
}
