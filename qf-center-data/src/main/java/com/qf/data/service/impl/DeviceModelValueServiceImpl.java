package com.qf.data.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.Pages;
import com.qf.common.constant.CacheConstant;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.DeviceModelValueService;
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
public class DeviceModelValueServiceImpl implements DeviceModelValueService {

    @Resource
    private RepositoryHandleService repositoryHandleService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void saveDeviceModelValue(DeviceModelValue deviceModelValue) {
        //判断 非空
        if (ObjectUtil.isNull(deviceModelValue)) {
            return;
        }

        //1添加时间
        deviceModelValue.setCreateTime(new Date());
        repositoryHandleService.save(deviceModelValue);
    }

    @Override
    public Page<DeviceModelValue> list(DeviceModelValueDto deviceModelValueDto) {
        Page<DeviceModelValue> deviceModelValuePage = new Page<>();

        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        //1. 查询条件
        //租户ID
        if (StrUtil.isNotEmpty(deviceModelValueDto.getTenant_id())) criteria.and("tenant_id").is(deviceModelValueDto.getTenant_id());
        //产品Key
        if (StrUtil.isNotEmpty(deviceModelValueDto.getProduct_key())) criteria.and("product_key").is(deviceModelValueDto.getProduct_key());
        //设备Key
        if (StrUtil.isNotEmpty(deviceModelValueDto.getDevice())) criteria.and("device").is(deviceModelValueDto.getDevice());
        //时间
        Pages page = deviceModelValueDto.getPage();
        if(page.getStartTime() > 0 && page.getEndTime() > 0 && page.getStartTime() <= page.getEndTime()){
            criteria.and("createTime").gte(new Date(page.getStartTime())).lte(new Date(page.getEndTime()));
        }

        //2. 集合名称
        String collection = CacheConstant.Entity.DEVICE + CacheConstant.Suffix.VALUE + "_" + deviceModelValueDto.getProduct_key();
        //3:Mongo查询总条数
        long count = mongoTemplate.count(query, collection);
        //4:分页
        query.limit((int)page.getSize()).skip(page.getSize()*(page.getCurrent()-1));
        //5:排序
        query.with(Sort.by(Sort.Direction.DESC,"createTime"));
        System.out.println("criteria:" + criteria.toString());
        System.out.println("query:" + query.toString());
        //8:查分页数据
        List<DeviceModelValue> deviceModelValueList = mongoTemplate.find(query, DeviceModelValue.class,collection);
        //9：构建分页对象
        deviceModelValuePage
                .setCurrent(page.getCurrent())
                .setSize(page.getSize())
                .setTotal(count)
                .setRecords(deviceModelValueList);

        return deviceModelValuePage;
    }

}
