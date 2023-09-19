package com.qf.data.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.Pages;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceCmdDto;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceCmd;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.DeviceCmdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/data/deviceCmd")
@Tag(name = "DeviceCmdController", description = "设备指令操作日志服务")
public class DeviceCmdController {

    @Autowired
    private DeviceCmdService deviceCmdService;

    @PostMapping("/list")
    @Operation(summary = "设备指令接口 - 分页查询",description = "根据条件查询设备指令")
    public R<Page<DeviceCmd>> list(@RequestBody DeviceCmdDto deviceCmdDto) {
        if (deviceCmdDto == null) {
            return R.fail("查询条件不可以为空");
        }
        if (StringUtils.isEmpty(deviceCmdDto.getProductKey())) {
            return R.fail("产品Key不可以为空");

        }
        if (StringUtils.isEmpty(deviceCmdDto.getDeviceKey())
                && StringUtils.isEmpty(deviceCmdDto.getProductKey())
                && StringUtils.isEmpty(deviceCmdDto.getTenantId())) {
            return R.fail("查询条件不可以为空");
        }
        //如果时间为空, 设置默认查询时间范围
        if (deviceCmdDto.getPage() == null) {
            deviceCmdDto.setPage(new Pages());
        }
        if (deviceCmdDto.getPage().getStartTime() == 0
                || deviceCmdDto.getPage().getEndTime() == 0) {
            //开始时间
            long endTime = System.currentTimeMillis();
            Calendar calBegin = Calendar.getInstance();
            calBegin.add(Calendar.DAY_OF_MONTH, -1);
            //结束时间
            long startTime = calBegin.getTimeInMillis();
            deviceCmdDto.getPage().setStartTime(startTime);
            deviceCmdDto.getPage().setEndTime(endTime);
        }
        Page<DeviceCmd> page = deviceCmdService.list(deviceCmdDto);
        return  R.ok(page);

    }
}
