package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.mapper.QfDeviceTopicMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfDeviceTopic;
import com.qf.service.service.IQfDeviceTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Topic主题表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfDeviceTopicServiceImpl implements IQfDeviceTopicService {

    @Autowired
    private QfDeviceTopicMapper deviceTopicMapper;

    @Override
    public QfDeviceTopic add(QfDeviceTopic type) {
        deviceTopicMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = deviceTopicMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfDeviceTopic update(QfDeviceTopic type) {
        deviceTopicMapper.updateById(type);
        return type;
    }

    @Override
    public QfDeviceTopic selectById(String id) {
        QfDeviceTopic deviceTopic = deviceTopicMapper.selectById(id);
        return deviceTopic;
    }

    @Override
    public Page<QfDeviceTopic> list(DeviceTopicDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfDeviceTopic> page = deviceTopicMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfDeviceTopic> all(DeviceTopicDto dto) {
        List<QfDeviceTopic> list = deviceTopicMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfDeviceTopic> fuzzyQuery(DeviceTopicDto dto) {
        LambdaQueryWrapper<QfDeviceTopic> queryWrapper = Wrappers.<QfDeviceTopic>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getTopic()), QfDeviceTopic::getTopic, dto.getTopic());
            queryWrapper.eq(dto.getDeviceKey() != null, QfDeviceTopic::getDeviceKey, dto.getDeviceKey());
        }
        return queryWrapper;
    }

}
