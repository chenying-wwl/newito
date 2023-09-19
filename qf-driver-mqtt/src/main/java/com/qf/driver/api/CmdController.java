package com.qf.driver.api;

import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import com.qf.common.dto.MqttUserDto;
import com.qf.driver.service.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 指令发送接口, 平台发送指令到mqtt服务器
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/driver/cmd")
@Tag(name = "CmdController", description = "平台指令发送")
public class CmdController {

    @Autowired
    private CommandService commandService;

    @PostMapping("/send")
    @Operation(summary = "指令发送",description = "发送指令到Mqtt服务器")
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto) {
        if (StringUtils.isEmpty(dto.getCmdName())
                || StringUtils.isEmpty(dto.getDeviceKey())
                || StringUtils.isEmpty(dto.getValue())) {
            return R.fail("设备Key, 指令名称, 指令值不允许为空!");
        }
        Boolean flag = commandService.write(dto);
        if (flag) {
            return R.ok("发送成功!");
        } else {
            return R.fail("发送失败!");
        }
    }
}
