package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.model.QfDeviceModel;
import com.qf.tenant.manager.feign.DeviceModelFeign;
import com.qf.tenant.manager.service.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelFeign deviceModelFeign;

    @Override
    public R add(QfDeviceModel qfDeviceModel) {
        qfDeviceModel.setCreateTime(new Date());
        qfDeviceModel.setUpdateTime(new Date());
        qfDeviceModel.setDeleted(0);
        R<QfDeviceModel> r = deviceModelFeign.add(qfDeviceModel);
        return r;
    }

    @Override
    public R updateDeleted(Long id, Integer deleted) {
        R<QfDeviceModel> r1 = deviceModelFeign.selectById(id+"");
        if(R.ok().isOk()){
            QfDeviceModel data = r1.getData();
            data.setDeleted(deleted);
            R<QfDeviceModel> r2 = deviceModelFeign.update(data);
            return r2;
        }
        return R.fail(deleted==0?"物模型绑定失败！":"物模型删除失败！");
    }

    @Override
    public R delete(Long id) {
        R<Boolean> r = deviceModelFeign.delete(id + "");
        return r;
    }

    @Override
    public R listDeviceModelByDeviceKey(String deviceKey) {
        DeviceModelDto deviceModelDto = new DeviceModelDto();
        deviceModelDto.setDeviceKey(deviceKey);
        R<List> r = deviceModelFeign.findAll(deviceModelDto);
        return r;
    }

}
