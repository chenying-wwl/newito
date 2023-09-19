package com.qf.user.manager.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 千锋健哥
 */
@FeignClient(name = "qf-driver-mqtt")
public interface CmdFeign {

    /**
     * 向设备发送指令
     * @param dto
     * @return
     */
    @PostMapping("/driver/cmd/send")
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto);
}
