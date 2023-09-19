package com.qf.data.service.impl;

import com.qf.common.constant.CommonConstant;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.RepositoryHandleService;
import com.qf.data.service.RepositoryService;
import com.qf.data.strategy.RepositoryStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 自定义数据处理，此处可以自定义逻辑，将数据存放到别的数据库，或者发送到别的地方
 *   保存数据位置有以下几种：
 *     1、redis
 *     2、mongodb
 *     3、es
 *     4、Influxdb
 *     5、Opentsdb
 *   但是我们本次案例使用的是：redis和mongodb
 *   @author 千锋健哥
 */
@Slf4j
@Service
public class RepositoryHandleServiceImpl implements RepositoryHandleService {

    /**
     * 开始保存
     */
    @Override
    public void save(DeviceModelValue deviceModelValue) {
        //1:使用Redis保存
        RepositoryService redisRepositoryService = RepositoryStrategyFactory.
                get(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_REDIS);
        redisRepositoryService.saveDeviceModelValue(deviceModelValue);

        //2:使用Mongo保存
        RepositoryService mongoRepositoryService = RepositoryStrategyFactory.
                get(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_MONGO);
        mongoRepositoryService.saveDeviceModelValue(deviceModelValue);
    }

    @Override
    public void saveCmd(DeviceCmd deviceCmd) {
        //1:使用Redis保存
        RepositoryService redisRepositoryService = RepositoryStrategyFactory.
                get(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_REDIS);
        redisRepositoryService.saveDeviceCmd(deviceCmd);

        //2:使用Mongo保存
        RepositoryService mongoRepositoryService = RepositoryStrategyFactory.
                get(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_MONGO);
        mongoRepositoryService.saveDeviceCmd(deviceCmd);
    }

}