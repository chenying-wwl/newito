package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.model.QfDevice;

import java.util.List;

public interface DeviceService {

    /**
     * 根据父级Id查询设备列表
     * @param parentId
     * @return
     */
    public R<List<QfDevice>> findByParentId(Long parentId);

    /**
     * 保存设备信息
     * @param deviceDto
     * @return
     */
    public R saveDevice(DeviceDto deviceDto);

    /**
     * 根据租户ID查询设备信息，关联查询设备所属产品
     * @param tenantId
     * @return
     */
    public R listDeviceByTenantId(String tenantId);

    /**
     * 根据条件查询设备列表信息
     */
    public R listDevices(DeviceDto deviceDto);

    /**
     * 删除设备
     * @param deviceId
     * @param code
     * @return
     */
    public R delDevice(Long deviceId,String phone, String code);

    /**
     * 添加设备到分组
     * @param deviceId
     * @param groupId
     * @return
     */
    R addDeviceToGroup(Long deviceId, Long groupId);

    /**
     * 从分组中移除设备
     * @param deviceId
     * @param groupId
     * @return
     */
    R removeDeviceFromGroup(Long deviceId, Long groupId);

    R delDevice(Long deviceId);

    R getDeviceById(String deviceId);

    /**
     * 根据产品ID，查询产品下的设备列表（无分页）
     * @param productId
     * @return
     */
    R listDevices(Long productId);

    R selectDeviceCount(String tenantId, Long productId);

    R listDevicesProduct(String productId);

    R listDevicesByTenant(String tenantId);

    R isOnline(String productKey, String deviceKey);

    R<List<QfDevice>> findAll(DeviceDto deviceDto);
}
