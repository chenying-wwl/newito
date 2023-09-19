package com.qf.api.service.impl;

import com.qf.api.feign.TenantFeign;
import com.qf.api.service.TenantService;
import com.qf.common.bean.R;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantFeign tenantFeign;

    @Override
    public QfTenant findTenantByAccessKey(String accessKey) {
        TenantDto dto = new TenantDto();
        dto.setAccessKey(accessKey);
        R<List<QfTenant>> result = tenantFeign.findTenantAll(dto);
        if (result != null) {
            List<QfTenant> list = result.getData();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

}
