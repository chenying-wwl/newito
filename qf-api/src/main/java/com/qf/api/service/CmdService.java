package com.qf.api.service;

import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 指令发送
 * @author 千锋健哥
 */
public interface CmdService {

    /**
     * 平台发送指令给mqtt服务器
     * @param dto
     * @return
     */
    public R<String> cmdSend(@RequestBody(required = true) CmdParamDto dto);;
}
