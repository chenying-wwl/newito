package com.qf.api.service.impl;

import com.qf.api.feign.CmdFeign;
import com.qf.api.service.CmdService;
import com.qf.common.bean.R;
import com.qf.common.dto.CmdParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 千锋健哥
 */
@Slf4j
@Service
public class CmdServiceImpl implements CmdService {

    @Autowired
    private CmdFeign cmdFeign;

    @Override
    public R<String> cmdSend(CmdParamDto dto) {
        R<String> result = cmdFeign.cmdSend(dto);
        return result;
    }

}
