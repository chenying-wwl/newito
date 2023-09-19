package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceTopic;
import com.qf.common.model.QfProduct;
import com.qf.common.utils.IdWorker;
import com.qf.tenant.manager.feign.*;
import com.qf.tenant.manager.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceFeign deviceFeign;

    @Autowired
    private DeviceTopicFeign deviceTopicFeign;

    @Autowired
    private CheckCodeFeign checkCodeFeign;

    @Autowired
    private DeviceHeartBeatFeign deviceHeartBeatFeign;

    @Autowired
    private ProductFeign productFeign;

    @Override
    public R<List<QfDevice>> findByParentId(Long parentId) {
        DeviceDto dto = new DeviceDto();
        dto.setParentId(parentId);
        R<List<QfDevice>> result = deviceFeign.findAll(dto);
        return result;
    }

    @Override
    public R saveDevice(DeviceDto deviceDto) {
        // 1.生成设备deviceKey
        String deviceKey = "device"+"_"+ new IdWorker().nextId();
        deviceDto.setDeviceKey(deviceKey);
        // 2.设置创建时间、更新时间、逻辑删除、激活状态
        deviceDto.setCreateTime(new Date());
        deviceDto.setUpdateTime(new Date());
        deviceDto.setDeleted(false);
        deviceDto.setActive(1);
        // 3.生成device
        deviceDto.setDeviceSecret(UUID.randomUUID().toString().replace("-",""));
        // 4.保存设备信息
        QfDevice qfDevice = new QfDevice();
        deviceDto.convertDtoToDo(qfDevice);
        R r = deviceFeign.add(qfDevice);
        //5.生成Topic信息 (分布式事务管理待实现)
        QfDeviceTopic deviceTopic = new QfDeviceTopic();
        String dataTopic = "/qfjava/device/data/"+deviceKey;
        deviceTopic.setTopic(dataTopic);
        deviceTopic.setCreateTime(new Date());
        deviceTopic.setUpdateTime(new Date());
        deviceTopic.setDeleted(false);
        deviceTopic.setDeviceKey(deviceKey);
        R<QfDeviceTopic> r1 = deviceTopicFeign.add(deviceTopic);

        String ctrlTopic = "/qfjava/device/ctrl/"+deviceKey;
        deviceTopic.setTopic(ctrlTopic);
        R<QfDeviceTopic> r2 = deviceTopicFeign.add(deviceTopic);

        return r;
    }

    @Override
    public R listDeviceByTenantId(String tenantId) {
        R<List<DeviceDto>> r = deviceFeign.lisByTenant(tenantId);
        return r;
    }

    @Override
    public R listDevices(DeviceDto deviceDto) {
        deviceDto.setDeleted(false);
        return deviceFeign.list(deviceDto);
    }

    @Override
    public R delDevice(Long deviceId,String phone,String code) {
        //1.短信验证码校验
        R r = checkCodeFeign.getCode(phone);
        if(r.isOk() && r.getData().equals(code)){
            //验证码正确,删除当前设备
            delDeviceById(deviceId);
            //注册完成，删除redis中的验证码
            checkCodeFeign.delCode(phone);
            return R.ok("删除设备成功！");
        }else{
            return R.fail("验证码错误，删除设备失败！");
        }
    }

    @Override
    public R addDeviceToGroup(Long deviceId, Long groupId) {
        R<QfDevice> r1 = deviceFeign.selectById(deviceId + "");
        if(r1.isOk()){
            QfDevice qfDevice = r1.getData();
            qfDevice.setGroupId(groupId);
            return deviceFeign.update(qfDevice);
        }else{
            return R.fail("设备ID不存在！");
        }
    }

    @Override
    public R removeDeviceFromGroup(Long deviceId, Long groupId) {
        R<QfDevice> r1 = deviceFeign.selectById(deviceId + "");
        if(r1.isOk()){
            QfDevice qfDevice = r1.getData();
            qfDevice.setGroupId(0L);
            return deviceFeign.update(qfDevice);
        }else{
            return R.fail("设备ID不存在！");
        }
    }

    @Override
    public R delDevice(Long deviceId) {
        try {
            delDeviceById(deviceId);
            return R.ok("删除设备成功！");
        }catch (Exception e){
            e.printStackTrace();
            return R.fail("删除设备失败！");
        }
    }

    @Override
    public R getDeviceById(String deviceId) {
        return deviceFeign.selectDetailById(deviceId);
    }

    @Override
    public R listDevices(Long productId) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setProductId(productId);
        deviceDto.setDeleted(false);
        R<List<QfDevice>> r = deviceFeign.findAll(deviceDto);
        return r;
    }

    @Override
    public R selectDeviceCount(String tenantId, Long productId) {
        //统计总数、未激活、已激活设备
        DeviceDto deviceDto = new DeviceDto();
        //过滤逻辑删除记录
        deviceDto.setDeleted(false);
        deviceDto.setTenantId(tenantId);
        //设置产品ID参数
        if(productId != 0){
            deviceDto.setProductId(productId);
        }
        //查询设备总数
        int allCount = deviceFeign.findAllCount(deviceDto).getData();
        //查询激活设备数
        deviceDto.setActive(2);
        int activeCount = deviceFeign.findAllCount(deviceDto).getData();

        //查询在线设备数
        int onLineCount = 0;
        if(productId != 0){
            R<QfProduct> productR = productFeign.selectById(productId+"");
            onLineCount = deviceHeartBeatFeign.onLineCount(productR.getData().getProductKey()).getData();
        }else{
            //根据租户ID查询查询所有产品
            ProductDto productDto = new ProductDto();
            productDto.setTenantId(tenantId);
            R<List<QfProduct>> productListR = productFeign.findAll(productDto);
            //遍历每一个查询，分别查询每个产品下的在线设备数，求和
            for (QfProduct product:productListR.getData()) {
                Integer num = deviceHeartBeatFeign.onLineCount(product.getProductKey()).getData();
                onLineCount+=num;
            }
        }

        //封装结果
        HashMap<String,Integer> countMap = new HashMap();
        countMap.put("allCount",allCount);
        countMap.put("activeCount",activeCount);
        countMap.put("onLineCount",onLineCount);
        //返回结果
        return R.ok(countMap);
    }

    @Override
    public R listDevicesProduct(String productId) {
        return deviceFeign.lisByProduct(productId);
    }

    @Override
    public R listDevicesByTenant(String tenantId) {
        return deviceFeign.lisByTenant(tenantId);
    }

    @Override
    public R isOnline(String productKey, String deviceKey) {
        return deviceHeartBeatFeign.deviceState(productKey,deviceKey);
    }

    @Override
    public R<List<QfDevice>> findAll(DeviceDto deviceDto) {
        return deviceFeign.findAll(deviceDto);
    }


    /**
     * 根据id删除设备：如果是子设备就直接删除，如果是父设备则先删除所有子设备再删除父设备
     * @param id
     */
    private void delDeviceById(Long id){
        //查询此设备是否有子设备
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setParentId(id);
        R<List<QfDevice>> r = deviceFeign.findAll(deviceDto);
        if(r.isOk() && r.getData().size() > 0){
            for (QfDevice qfDevice: r.getData() ) {
                delDeviceById(qfDevice.getId());
            }
        }else{
            //执行设备逻辑删除
            R<QfDevice> r1 = deviceFeign.selectById(id + "");
            QfDevice qfDevice = r1.getData();
            qfDevice.setDeleted(true);
            deviceFeign.update(qfDevice);
        }
    }

}
