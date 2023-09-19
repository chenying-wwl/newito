package com.qf.driver.service;

import com.qf.common.dto.CmdParamDto;

/**
 * 平台向设备发送指令业务
 * @author 千锋健哥
 */
public interface CommandService {

    /**
     * 写操作
     *
     */
    Boolean write(CmdParamDto dto);

}
