

package com.qf.data.utils;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 千锋健哥
 */
@Slf4j
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加 Key 缓存
     *
     * @param key   String key
     * @param value Object
     */
    public void setKey(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 添加 Key 缓存,并设置失效时间
     *
     * @param key   String key
     * @param value Object
     * @param time  Time
     * @param unit  TimeUnit
     */
    public void setKey(String key, Object value, long time, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, time, unit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 批量添加 Key 缓存
     *
     * @param valuesMap Map<String, Object>
     */
    public void setKey(Map<String, Object> valuesMap) {
        try {
            redisTemplate.opsForValue().multiSet(valuesMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 批量添加 Key 缓存,并设置失效时间
     *
     * @param valueMap     Map<String, Object>
     * @param expireMillis Map<String, Long>
     */
    public void setKey(Map<String, Object> valueMap, Map<String, Long> expireMillis) {
        try {
            redisTemplate.opsForValue().multiSet(valueMap);
            setExpire(expireMillis);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public Object getKeyByemail(String key,Class<Object> type) {
        try {
            Object object = redisTemplate.opsForValue().get(key);
            return object;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    /**
     * 获取 Key 缓存
     *
     * @param key String key
     * @param <T> T
     * @return T
     */
    public <T> T getKey(String key, Class<T> type) {
        try {
            Object object = redisTemplate.opsForValue().get(key);
            return Convert.convert(type, object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 批量获取 Key 缓存
     *
     * @param keys String key array
     * @param <T>  T
     * @return T Array
     */
    public <T> List<T> getKey(List<String> keys, Class<T> type) {
        try {
            List<Object> objects = redisTemplate.opsForValue().multiGet(keys);
            return Convert.toList(type, objects);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 删除 Key 缓存
     *
     * @param key Key
     */
    public void deleteKey(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 批量删除 Key 缓存
     *
     * @param keys Key Array
     */
    public void deleteKey(List<String> keys) {
        if (null != keys && keys.size() > 0) {
            try {
                redisTemplate.delete(keys);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 判断 Key 是否存在
     *
     * @param key String key
     * @return boolean
     */
    public boolean hasKey(String key) {
        try {
            Boolean hasKey = redisTemplate.hasKey(key);
            return null != hasKey ? hasKey : false;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 指定键值失效时间
     *
     * @param key  String key
     * @param time Time
     * @param unit TimeUnit
     */
    public void setExpire(String key, long time, TimeUnit unit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, unit);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 批量指定键值失效时间
     *
     * @param expireMillis Map<String, Long>
     */
    public void setExpire(Map<String, Long> expireMillis) {
        try {
            if (null != expireMillis && expireMillis.size() > 0) {
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                redisTemplate.execute((RedisCallback<Object>) connection -> {
                    expireMillis.forEach((key, expire) -> {
                        byte[] serialize = stringRedisSerializer.serialize(key);
                        if (null != serialize) {
                            connection.pExpire(serialize, expire);
                        }
                    });
                    return null;
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 指定键值在指定时间失效
     *
     * @param key  String key
     * @param date Date
     */
    public void setExpireAt(String key, Date date) {
        try {
            Date current = new Date();
            if (date.getTime() >= current.getTime()) {
                redisTemplate.expireAt(key, date);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取 Key 失效时间
     *
     * @param key  String key
     * @param unit TimeUnit
     * @return long
     */
    public long getExpire(String key, TimeUnit unit) {
        try {
            Long expire = redisTemplate.getExpire(key, unit);
            return null != expire ? expire : 0L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 向set中添加指定的数据
     *
     * @param key
     * @param members
     * @return
     */
    public long sAdd(String key, Set<String> members) {

        try {
            Long count = redisTemplate.opsForSet().add(key, members.toArray());
            return null != count ? count : 0L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    /**
     * 获取set格式的数据
     * @param key
     * @param type 期望的数据类型
     * @return
     * @param <T>
     */
    public <T> Set<T> sMembers(String key, Class<T> type) {
        try {
            Set<Object> members = redisTemplate.opsForSet().members(key);
            return Convert.toSet(type, members);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 返回满足条件的key
     * @param key
     * @return
     */
    public Set<String> keys(String key) {
        try {
            Set<String> keySet = redisTemplate.keys(key);
            return keySet;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
