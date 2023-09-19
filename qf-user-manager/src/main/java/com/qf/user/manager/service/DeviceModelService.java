package com.qf.user.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 设备物模型
 * @author 千锋健哥
 */
public interface DeviceModelService {

    /**
     * 查询设备物模型所有数据
     * @param dto
     * @return
     */
    public R<List> findAll(DeviceModelDto dto);
}
