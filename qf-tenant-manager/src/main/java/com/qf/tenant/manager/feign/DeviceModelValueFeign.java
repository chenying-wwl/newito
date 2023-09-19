package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qf-center-data")
public interface DeviceModelValueFeign {

    @PostMapping("/data/deviceModelValue/list")
    public R<Page<DeviceModelValue>> list(@RequestBody DeviceModelValueDto deviceModelValueDto);
}
