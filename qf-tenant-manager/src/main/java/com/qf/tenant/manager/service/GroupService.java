package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.GroupDto;
import com.qf.common.model.QfGroup;

public interface GroupService {

    /**
     * 添加设备分组
     * @param qfGroup
     * @return
     */
    public R saveGroup(QfGroup qfGroup);

    /**
     * 根据租户ID查询设备分组列表
     * @param tenantId
     * @return
     */
    public R listGroup(GroupDto groupDto);
}
