package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.mapper.MqttUserMapper;
import com.qf.common.model.MqttUser;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceModel;
import com.qf.service.service.MqttUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class MqttUserServiceImpl implements MqttUserService {

    @Autowired
    private MqttUserMapper mqttUserMapper;


    @Override
    public MqttUser add(MqttUser type) {
        type.setCreated(new Date());
        type.setIsSuperuser(false);
        mqttUserMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int count = mqttUserMapper.deleteById(id);
        return count > 0;
    }

    @Override
    public MqttUser update(MqttUser type) {
        mqttUserMapper.updateById(type);
        return type;
    }

    @Override
    public MqttUser selectById(String id) {
        MqttUser user = mqttUserMapper.selectById(id);
        return user;
    }

    @Override
    public Page<MqttUser> list(MqttUserDto dto) {
        Page<MqttUser> page = mqttUserMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));
        return page;
    }

    @Override
    public List<MqttUser> all(MqttUserDto dto) {
        List<MqttUser> userList = mqttUserMapper.selectList(fuzzyQuery(dto));
        return userList;
    }

    @Override
    public LambdaQueryWrapper<MqttUser> fuzzyQuery(MqttUserDto dto) {
        LambdaQueryWrapper<MqttUser> queryWrapper = Wrappers.<MqttUser>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.eq(StrUtil.isNotBlank(dto.getUsername()), MqttUser::getUsername, dto.getUsername());
            queryWrapper.eq(StrUtil.isNotBlank(dto.getTenantId()), MqttUser::getTenantId, dto.getTenantId());
        }
        return queryWrapper;
    }

}
