package com.qf.data.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.Pages;
import com.qf.common.constant.CacheConstant;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.DeviceCmdService;
import com.qf.data.service.RepositoryHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Slf4j
@Service
public class DeviceCmdServiceImpl implements DeviceCmdService {

    @Resource
    private RepositoryHandleService repositoryHandleService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDeviceCmd(DeviceCmd deviceCmd) {
        //判断 非空
        if (ObjectUtil.isNull(deviceCmd)) {
            return;
        }

        //1添加时间
        deviceCmd.setCreateTime(new Date());
        repositoryHandleService.saveCmd(deviceCmd);
    }

    @Override
    public Page<DeviceCmd> list(DeviceCmdDto deviceCmdDto) {
        Page<DeviceCmd> deviceModelValuePage = new Page<>();

        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        //1. 查询条件
        //租户ID
        if (StrUtil.isNotEmpty(deviceCmdDto.getTenantId())) criteria.and("tenantId").is(deviceCmdDto.getTenantId());
        //产品Key
        if (StrUtil.isNotEmpty(deviceCmdDto.getProductKey())) criteria.and("productKey").is(deviceCmdDto.getProductKey());
        //设备Key
        if (StrUtil.isNotEmpty(deviceCmdDto.getDeviceKey())) criteria.and("deviceKey").is(deviceCmdDto.getDeviceKey());
        //物模型名称
        if (StrUtil.isNotEmpty(deviceCmdDto.getCmdName())) criteria.and("cmdName").is(deviceCmdDto.getCmdName());
        //时间
        Pages page = deviceCmdDto.getPage();
        if(page.getStartTime() > 0 && page.getEndTime() > 0 && page.getStartTime() <= page.getEndTime()){
            criteria.and("createTime").gte(new Date(page.getStartTime())).lte(new Date(page.getEndTime()));
        }

        //2. 集合名称
        String collection = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.CMD + "_" + deviceCmdDto.getProductKey();
        //3:Mongo查询总条数
        long count = mongoTemplate.count(query, collection);
        //4:分页
        query.limit((int)page.getSize()).skip(page.getSize()*(page.getCurrent()-1));
        //5:排序
        query.with(Sort.by(Sort.Direction.DESC,"createTime"));
        System.out.println("criteria:" + criteria.toString());
        System.out.println("query:" + query.toString());
        //8:查分页数据
        List<DeviceCmd> deviceCmdList = mongoTemplate.find(query, DeviceCmd.class,collection);
        //9：构建分页对象
        deviceModelValuePage
                .setCurrent(page.getCurrent())
                .setSize(page.getSize())
                .setTotal(count)
                .setRecords(deviceCmdList);

        return deviceModelValuePage;
    }

}
