package com.qf.gateway.service;


/**
 * @author 千锋健哥
 */
public interface TenantService {

    /**
     * 判断租户accessKey和accessSecret是否正确
     * @param accessKey
     * @param accessSecret
     * @return
     */
    public Boolean findByAccessKey(String accessKey, String accessSecret);
}
