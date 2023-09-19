package com.qf.user.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfProduct;
import com.qf.user.manager.feign.DeviceFeign;
import com.qf.user.manager.feign.HeartBeatFeign;
import com.qf.user.manager.feign.ProductFeign;
import com.qf.user.manager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 千锋健哥
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceFeign deviceFeign;

    @Autowired
    private HeartBeatFeign heartBeatFeign;

    @Autowired
    private ProductFeign productFeign;

    @Override
    public R<List> findAreaAll(DeviceDto dto) {
        R<List> list = deviceFeign.findAreaAll(dto);
        return list;
    }

    @Override
    public R<QfDevice> selectById(String id) {
        R<QfDevice> device = deviceFeign.selectById(id);
        return device;
    }

    @Override
    public R<QfDevice> selectByDeviceKey(String deviceKey) {
        DeviceDto dto = new DeviceDto();
        dto.setDeviceKey(deviceKey);
        R<List> result = deviceFeign.findAreaAll(dto);
        if (result.getData() != null && result.getData().size() > 0){
            QfDevice qfDevice = BeanUtil.toBean(result.getData().get(0), QfDevice.class);
            return R.ok(qfDevice);
        }
        return R.fail(new QfDevice());
    }

    @Override
    public R<Page<QfDevice>> list(DeviceDto dto) {
        //声明产品Map, key是产品ID, value是productKey
        Map<Long, String> productMap = new HashMap<>();
        //查询产品列表
        R<List> productResult = productFeign.findAreaAll(new ProductDto());
        if (productResult.getData() != null) {
            List<QfProduct> qfProducts = BeanUtil.copyToList(productResult.getData(), QfProduct.class);
            for (QfProduct qfProduct : qfProducts) {
                productMap.put(qfProduct.getId(), qfProduct.getProductKey());
            }
        }

        //查询设备列表
        R<Page<QfDevice>> page = deviceFeign.list(dto);
        if (page.getData() != null) {
            if (page.getData().getRecords() != null && page.getData().getRecords().size() > 0) {
                for (QfDevice device : page.getData().getRecords()) {
                    //通过产品ID获取productKey
                    String productKey = productMap.get(device.getProductId());
                    R<Boolean> booleanR = heartBeatFeign.deviceState(productKey, device.getDeviceKey());
                    device.setState(booleanR.getData());
                }
            }
        }
        return page;
    }

}
