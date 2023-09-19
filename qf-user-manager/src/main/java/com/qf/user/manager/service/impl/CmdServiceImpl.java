package com.qf.user.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfProduct;
import com.qf.user.manager.feign.DeviceCmdFeign;
import com.qf.user.manager.service.CmdService;
import com.qf.user.manager.service.DeviceService;
import com.qf.user.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class CmdServiceImpl implements CmdService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceCmdFeign deviceCmdFeign;


    @Override
    public R<Page<DeviceCmd>> list(DeviceCmdDto deviceCmdDto) {
        //根据设备key查询设备对象
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceKey(deviceCmdDto.getDeviceKey());
        R<List> deviceResult = deviceService.findAreaAll(deviceDto);

        if (deviceResult.getData() != null && deviceResult.getData().size() > 0) {
            QfDevice qfDevice = BeanUtil.toBean(deviceResult.getData().get(0), QfDevice.class);

            //根据设备key查询租户ID
            R<QfProduct> productResult = productService.selectById(String.valueOf(qfDevice.getProductId()));
            if (productResult.getData() != null) {
                deviceCmdDto.setProductKey(productResult.getData().getProductKey());
            }

            //根据设备Key查询产品Key
            deviceCmdDto.setTenantId(qfDevice.getTenantId());
        }

        R<Page<DeviceCmd>> result = deviceCmdFeign.list(deviceCmdDto);
        return result;
    }

}
