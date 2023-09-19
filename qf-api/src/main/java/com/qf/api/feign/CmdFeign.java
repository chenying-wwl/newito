package com.qf.api.feign;

import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 指令发送
 * @author 千锋健哥
 */
@FeignClient(name = "qf-driver-mqtt")
public interface CmdFeign {

    /**
     * 平台发送指令给mqtt服务器
     * @param dto
     * @return
     */
    @PostMapping("/driver/cmd/send")
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto);
}
