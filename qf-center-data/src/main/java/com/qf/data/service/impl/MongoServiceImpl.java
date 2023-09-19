
package com.qf.data.service.impl;


import cn.hutool.core.util.StrUtil;
import com.qf.common.constant.CacheConstant;
import com.qf.common.constant.CommonConstant;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.RepositoryService;
import com.qf.data.strategy.RepositoryStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 *
 * @author 千锋健哥
 */
@Slf4j
@Service
public class MongoServiceImpl implements RepositoryService, InitializingBean {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void afterPropertiesSet() {
        RepositoryStrategyFactory.put(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY_MONGO, this);
    }


    @Override
    public void saveDeviceModelValue(DeviceModelValue deviceModelValue) {
        if (!StrUtil.isAllNotEmpty(deviceModelValue.getDevice(), deviceModelValue.getProduct_key())) {
            return;
        }
        //Mongo集合
        String collection = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.VALUE + "_" + deviceModelValue.getProduct_key();
        //给集合创建索引
        ensureDeviceModelValueIndex(collection);
        //保存文档到集合中
        mongoTemplate.insert(deviceModelValue, collection);
    }

    @Override
    public void saveDeviceCmd(DeviceCmd deviceCmd) {
        if (!StrUtil.isAllNotEmpty(deviceCmd.getDeviceKey(), deviceCmd.getProductKey())) {
            return;
        }
        //Mongo集合
        String collection = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.CMD + "_" + deviceCmd.getProductKey();
        //给集合创建索引
        ensureDeviceCmdIndex(collection);
        //保存文档到集合中
        mongoTemplate.insert(deviceCmd, collection);
    }

    /**
     * 给DeviceCmd集合创建索引
     * @param collection
     */
    private void ensureDeviceCmdIndex(String collection) {
        //创建索引
        Index deviceIndex = new Index();
        deviceIndex.background()
                .on("deviceKey", Sort.Direction.DESC)
                .named("IX_device_key");
        mongoTemplate.indexOps(collection).ensureIndex(deviceIndex);

        //创建索引
        Index tenantIdIndex = new Index();
        tenantIdIndex.background()
                .on("tenantId", Sort.Direction.DESC)
                .named("IX_tenant_id");
        mongoTemplate.indexOps(collection).ensureIndex(tenantIdIndex);

        //创建索引
        Index cmdNameIndex = new Index();
        cmdNameIndex.background()
                .on("cmdName", Sort.Direction.DESC)
                .named("IX_cmd_name");
        mongoTemplate.indexOps(collection).ensureIndex(cmdNameIndex);

        //创建索引
        Index productKey = new Index();
        productKey.background()
                .on("productKey", Sort.Direction.DESC)
                .named("IX_product_key");
        mongoTemplate.indexOps(collection).ensureIndex(productKey);

        //创建索引
        Index timeIndex = new Index();
        timeIndex.background()
                .on("createTime", Sort.Direction.DESC)
                .named("IX_create_time");
        mongoTemplate.indexOps(collection).ensureIndex(timeIndex);
    }

    /**
     * 给DeviceModelValue集合创建索引
     */
    private void ensureDeviceModelValueIndex(String collection) {
        //创建索引
        Index deviceIndex = new Index();
        deviceIndex.background()
                .on("device", Sort.Direction.DESC)
                .named("IX_device_key");
        mongoTemplate.indexOps(collection).ensureIndex(deviceIndex);

        //创建索引
        Index tenantIdIndex = new Index();
        tenantIdIndex.background()
                .on("tenant_id", Sort.Direction.DESC)
                .named("IX_tenant_id");
        mongoTemplate.indexOps(collection).ensureIndex(tenantIdIndex);

        //创建索引
        Index productKeyIndex = new Index();
        productKeyIndex.background()
                .on("product_key", Sort.Direction.DESC)
                .named("IX_product_key");
        mongoTemplate.indexOps(collection).ensureIndex(productKeyIndex);

        //创建索引
        Index timeIndex = new Index();
        timeIndex.background()
                .on("createTime", Sort.Direction.DESC)
                .named("IX_create_time");
        mongoTemplate.indexOps(collection).ensureIndex(timeIndex);
    }

}