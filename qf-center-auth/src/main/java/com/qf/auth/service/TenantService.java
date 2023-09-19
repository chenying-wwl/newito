package com.qf.auth.service;

import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;

public interface TenantService {

    /**
     * 检查用户名是否可用
     * @param name
     * @return
     */
    public R checkNameCanBeUsed(String name);

    /**
     * 租户注册业务
     * @param tenantDto
     * @return
     */
    public R tenantRegist(TenantDto tenantDto);

    public R regVerify(TenantDto tenantDto);
    /**
     * 租户登录认证
     * @return
     */
    public R tenantAuth(TenantDto tenantDto);


}
