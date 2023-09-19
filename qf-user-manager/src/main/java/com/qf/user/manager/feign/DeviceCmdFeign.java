package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.model.DeviceCmd;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 设备操作指令日志查询
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-data")
public interface DeviceCmdFeign {

    /**
     * 查询设备操作指令日志
     * @param deviceCmdDto
     * @return
     */
    @PostMapping("/data/deviceCmd/list")
    public R<Page<DeviceCmd>> list(@RequestBody DeviceCmdDto deviceCmdDto);
}
