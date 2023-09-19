package com.qf.gateway.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.nacos.common.http.HttpRestResult;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import com.qf.gateway.feign.TenantFeign;
import com.qf.gateway.service.TenantService;
import com.qf.gateway.utils.AutowiredBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 千锋健哥
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantFeign tenantFeign;

    @Override
    public Boolean findByAccessKey(String accessKey, String accessSecret) {
        //根据accessKey到数据库获取租户对象
        TenantDto dto = new TenantDto();
        dto.setAccessKey(accessKey);

        /**
         * Gateway网关中接口不可以同步调用, 改为异步调用feign接口
         */
        R<List<QfTenant>> result = null;
        try {
            result = ThreadUtil.execAsync(new Callable<R<List<QfTenant>>>() {
                @Override
                public R<List<QfTenant>> call() throws Exception {
                    R<List<QfTenant>> result = tenantFeign.findTenantAll(dto);
                    return result;
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            if (result.getData() != null && result.getData().size() > 0) {
                QfTenant tenant = result.getData().get(0);
                //判断秘钥是否正确
                if (tenant.getAccessSecurit().equals(accessSecret)) {
                    return true;
                }
            }
        }

        return false;
    }

}
