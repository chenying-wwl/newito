package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTopicDto;
import com.qf.common.model.QfDeviceTopic;
import com.qf.tenant.manager.feign.DeviceTopicFeign;
import com.qf.tenant.manager.service.DeviceTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTopicServiceImpl implements DeviceTopicService {

    @Autowired
    private DeviceTopicFeign deviceTopicFeign;

    public R addTopic(QfDeviceTopic qfDeviceTopic) {
        R<QfDeviceTopic> r = deviceTopicFeign.add(qfDeviceTopic);
        return r;
    }

    @Override
    public R listByDeviceKey(String deviceKey) {
        DeviceTopicDto deviceTopicDto = new DeviceTopicDto();
        deviceTopicDto.setDeviceKey(deviceKey);
        R<List> r = deviceTopicFeign.findAll(deviceTopicDto);
        return r;
    }

}
