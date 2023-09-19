
package com.qf.data.service.impl;

import cn.hutool.core.util.StrUtil;
import com.qf.common.constant.CacheConstant;
import com.qf.common.constant.CommonConstant;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.RepositoryService;
import com.qf.data.strategy.RepositoryStrategyFactory;
import com.qf.data.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 千锋健哥
 */
@Slf4j
@Service
public class RedisServiceImpl implements RepositoryService, InitializingBean {

    @Resource
    private RedisUtil redisUtil;


    @Override
    public void afterPropertiesSet() {
        RepositoryStrategyFactory.put(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_REDIS, this);
    }

    @Override
    public void saveDeviceModelValue(DeviceModelValue deviceModelValue) {
        log.info("=====传感器数据未存储到Redis=========");
    }

    @Override
    public void saveDeviceCmd(DeviceCmd deviceCmd) {
        log.info("=====指令未存储到Redis====");
    }

}