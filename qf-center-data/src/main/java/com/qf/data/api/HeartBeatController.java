package com.qf.data.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelValueDto;
import com.qf.common.model.DeviceModelValue;
import com.qf.data.service.HeartBeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 设备心跳接口
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/data/heartBeat")
@Tag(name = "HeartBeatController", description = "设备心跳数据服务")
public class HeartBeatController {

    @Autowired
    private HeartBeatService heartBeatService;

    @GetMapping("/deviceState/{productKey}/{deviceKey}")
    @Operation(summary = "设备心跳数据接口", description = "根据设备Key查询设备是否在线, true在线, false离线")
    public R<Boolean> deviceState(@PathVariable(value = "productKey") String productKey,
                                  @PathVariable(value = "deviceKey") String deviceKey) {
        if (StringUtils.isEmpty(productKey)) {
            return R.fail("productKey不可以为空");
        }
        if (StringUtils.isEmpty(deviceKey)) {
            return R.fail("deviceKey不可以为空");
        }

        Boolean state = heartBeatService.findState(productKey, deviceKey);
        return R.ok(state);
    }

    @GetMapping("/deviceState/{productKey}")
    @Operation(summary = "查询在线设备数量接口", description = "根据productKey统计在线设备数量")
    public R<Integer> onLineCount(@PathVariable(value = "productKey") String productKey) {
        if (StringUtils.isEmpty(productKey)) {
            return R.fail("productKey不可以为空");
        }
        Integer count = heartBeatService.onLineCount(productKey);
        return R.ok(count);
    }

}