package com.qf.data.service.impl;

import cn.hutool.core.util.StrUtil;
import com.qf.common.constant.CacheConstant;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.HeartBeatService;
import com.qf.data.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 千锋健哥
 */
@Service
public class HeartBeatServiceImpl implements HeartBeatService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void SavePing(String productKey, String deviceKey) {
        if (!StrUtil.isAllNotEmpty(deviceKey)) {
            return;
        }
        //保存心跳
        String key = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.PING
                + "_" + productKey
                + "_" + deviceKey;
        redisUtil.setKey(key, "1", 30 , TimeUnit.SECONDS);
    }

    @Override
    public Boolean findState(String productKey, String deviceKey) {
        String key = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.PING
                + "_" + productKey
                + "_" + deviceKey;
        String value = redisUtil.getKey(key, String.class);
        if ("1".equals(value)){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Integer onLineCount(String productKey) {
        String key = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.PING
                + "_" + productKey + "*";
        Set<String> keySet = redisUtil.keys(key);
        if (keySet != null) {
            return keySet.size();
        } else {
            return 0;
        }
    }

}
