package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.model.QfDevice;
import com.qf.common.model.QfDeviceModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "qf-center-service")
public interface DeviceModelFeign {

    @PostMapping("/service/device-model/add")
    public R<QfDeviceModel> add(@RequestBody QfDeviceModel deviceModel);

    @GetMapping("/service/device-model/id/{id}")
    public R<QfDeviceModel> selectById(@PathVariable(value = "id") String id);

    @PostMapping("/service/device-model/update")
    public R<QfDeviceModel> update(@RequestBody QfDeviceModel deviceModel);

    @PostMapping("/service/device-model/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    @PostMapping("/service/device-model/all")
    public R<List> findAll(@RequestBody(required = false) DeviceModelDto dto);
}
