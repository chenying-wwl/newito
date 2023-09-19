package com.qf.user.manager.feign;

import com.qf.common.bean.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 设备心跳接口
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-data")
public interface HeartBeatFeign {

    /**
     * 查询设备状态, true在线, false离线
     * @param deviceKey
     * @return
     */
    @GetMapping("/data/heartBeat/deviceState/{productKey}/{deviceKey}")
    @Operation(summary = "设备心跳数据接口", description = "根据设备Key查询设备是否在线, true在线, false离线")
    public R<Boolean> deviceState(@PathVariable(value = "productKey") String productKey,
                                  @PathVariable(value = "deviceKey") String deviceKey);
}
