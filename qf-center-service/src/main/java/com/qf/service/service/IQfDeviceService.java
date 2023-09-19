package com.qf.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.common.base.Service;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;

import java.util.List;

/**
 * 设备表 服务类
 *
 * @author 千锋健哥
 */
public interface IQfDeviceService extends Service<QfDevice, DeviceDto> {

    /**
     * 根据租户ID查询设备列表
     */
    public List<DeviceDto> listDeviceByTenantId(String tenantId);

    /**
     * 根据产品ID查询设备列表
     * @param productId
     * @return
     */
    public List<DeviceDto> listDeviceByProductId(String productId);


    public DeviceDto selectByDeviceId(String deviceId);

    /**
     * 计算设备总数以及激活设备数量
     * @param dto
     * @return
     */
    public Integer findDeviceCount(DeviceDto dto);
}
