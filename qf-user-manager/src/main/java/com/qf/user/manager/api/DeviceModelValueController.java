package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.Pages;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import com.qf.user.manager.service.DeviceModelValueService;
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
 * 查询设备上报数据
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/deviceModelValue")
@Tag(name = "DeviceModelValueController", description = "设备数据服务")
public class DeviceModelValueController {

    @Autowired
    private DeviceModelValueService deviceModelValueService;

    @PostMapping("/list")
    @Operation(summary = "设备数据接口 - 分页查询",description = "根据条件查询设备数据")
    public R<Page<DeviceModelValue>> list(@RequestBody DeviceModelValueDto deviceModelValueDto) {
        if (deviceModelValueDto == null) {
            return R.fail("查询条件不可以为空");
        }
        if (StringUtils.isEmpty(deviceModelValueDto.getDevice())) {
            return R.fail("设备Key不可以为空");
        }
        //如果时间为空, 设置默认查询时间范围
        if (deviceModelValueDto.getPage() == null) {
            deviceModelValueDto.setPage(new Pages());
        }
        if (deviceModelValueDto.getPage().getStartTime() == 0
                || deviceModelValueDto.getPage().getEndTime() == 0) {
            //开始时间
            long endTime = System.currentTimeMillis();
            Calendar calBegin = Calendar.getInstance();
            calBegin.add(Calendar.DAY_OF_MONTH, -1);
            //结束时间
            long startTime = calBegin.getTimeInMillis();
            deviceModelValueDto.getPage().setStartTime(startTime);
            deviceModelValueDto.getPage().setEndTime(endTime);
        }
        R<Page<DeviceModelValue>> result = deviceModelValueService.list(deviceModelValueDto);
        return  result;

    }
}
