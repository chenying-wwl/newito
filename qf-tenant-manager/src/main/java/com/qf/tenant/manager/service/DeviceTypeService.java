package com.qf.tenant.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceTypeDto;
import com.qf.common.model.QfDeviceType;

import java.util.List;

/**
 * 设备类型管理
 * @author 千锋健哥
 */
public interface DeviceTypeService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll();

}
