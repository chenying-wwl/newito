package com.qf.api.controller;

import com.qf.api.service.CmdService;
import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方业务系统发送指令给mqtt服务器
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/api/cmd")
@Tag(name = "CmdController", description = "指令发送")
public class CmdController {

    @Autowired
    private CmdService cmdService;

    @PostMapping("/send")
    @Operation(summary = "指令发送",description = "发送指令到Mqtt服务器")
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto) {
        R<String> result = cmdService.cmdSend(dto);
        return result;
    }
}
