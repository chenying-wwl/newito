package com.qf.api.service;

import com.qf.common.model.QfTenant;

/**
 * @author 千锋健哥
 */
public interface TenantService {

    /**
     * 根据key获取租户对象
     * @param accessKey
     * @return
     */
    public QfTenant findTenantByAccessKey(String accessKey);
}
