package com.qf.tenant.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "qf-center-data")
public interface DeviceCmdFeign {

    @PostMapping("/data/deviceCmd/list")
    public R<Page<DeviceCmd>> list(@RequestBody DeviceCmdDto deviceCmdDto);

}
