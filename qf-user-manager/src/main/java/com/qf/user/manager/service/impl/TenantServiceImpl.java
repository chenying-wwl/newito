package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.constant.ValueConstant;
import com.qf.common.dto.TenantDto;
import com.qf.common.model.QfTenant;
import com.qf.user.manager.feign.TenantFeign;
import com.qf.user.manager.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户管理
 * @author 千锋健哥
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantFeign tenantFeign;

    @Override
    public R<List> findAreaAll(TenantDto dto) {
        R<List> list = tenantFeign.findAreaAll(dto);
        return list;
    }

    @Override
    public R<QfTenant> selectById(String id) {
        R<QfTenant> tenant = tenantFeign.selectById(id);
        return tenant;
    }

    @Override
    public R<Page<QfTenant>> list(TenantDto dto) {
        R<Page<QfTenant>> page = tenantFeign.list(dto);
        return page;
    }

    @Override
    public R<Boolean> checkTenantYes(String id) {
        try {
            QfTenant tenant = new QfTenant();
            tenant.setId(id);
            tenant.setAuditStatus(ValueConstant.Tenant.CHECK_YES);
            tenantFeign.update(tenant);
            return R.ok(true);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return R.fail(false);
    }

    @Override
    public R<Boolean> checkTenantNo(String id) {
        try {
            QfTenant tenant = new QfTenant();
            tenant.setId(id);
            tenant.setAuditStatus(ValueConstant.Tenant.CHECK_NO);
            tenantFeign.update(tenant);
            return R.ok(true);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return R.fail(false);
    }

    @Override
    public R<Boolean> isEnableTenantYes(String id) {
        try {
            QfTenant tenant = new QfTenant();
            tenant.setId(id);
            tenant.setEnable(ValueConstant.Tenant.ENABLE_YES);
            tenantFeign.update(tenant);
            return R.ok(true);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return R.fail(false);
    }

    @Override
    public R<Boolean> isEnableTenantNo(String id) {
        try {
            QfTenant tenant = new QfTenant();
            tenant.setId(id);
            tenant.setEnable(ValueConstant.Tenant.ENABLE_NO);
            tenantFeign.update(tenant);
            return R.ok(true);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return R.fail(false);
    }


}
