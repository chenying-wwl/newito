package com.qf.driver.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.model.MqttUser;
import com.qf.common.utils.SecuretUtil;
import com.qf.driver.feign.MqttUserFeign;
import com.qf.driver.service.MqttUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class MqttUserServiceImpl implements MqttUserService {

    @Autowired
    private MqttUserFeign mqttUserFeign;


    @Override
    public R<List> findAreaAll(MqttUserDto dto) {
        R<List> list = mqttUserFeign.findAreaAll(dto);
        return list;
    }

    @Override
    public R<MqttUser> selectById(String id) {
        R<MqttUser> result = mqttUserFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<MqttUser>> list(MqttUserDto dto) {
        R<Page<MqttUser>> page = mqttUserFeign.list(dto);
        return page;
    }

    @Override
    public R<MqttUser> add(MqttUser area) {
        R<MqttUser> result = mqttUserFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = mqttUserFeign.delete(id);
        return result;
    }

    @Override
    public R<MqttUser> update(MqttUser area) {
        R<MqttUser> result = mqttUserFeign.update(area);
        return result;
    }

    @Override
    public void addMqttUser(String tenantId) {
        //1. 查询该租户是否有mqtt服务器账户
        MqttUserDto mqttUserDto = new MqttUserDto();
        mqttUserDto.setUsername(tenantId);
        R<List> mqttUserResult = mqttUserFeign.findAreaAll(mqttUserDto);
        //2. 判断如果该租户没有mqtt服务器账号则创建
        if (mqttUserResult.getData() == null
                || mqttUserResult.getData().size() == 0) {
            MqttUser mqttUser = new MqttUser();
            //租户id作为mqtt服务器账户密码
            mqttUser.setTenantId(tenantId);
            mqttUser.setUsername(tenantId);
            mqttUser.setPassword(SecuretUtil.encrypt3ToMD5(tenantId));
            mqttUserFeign.add(mqttUser);
        }

    }

}
