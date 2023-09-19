package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.TenantDto;
import com.qf.common.mapper.QfTenantMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfTenant;
import com.qf.service.service.IQfTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfTenantServiceImpl implements IQfTenantService {

    @Autowired
    private QfTenantMapper tenantMapper;

    @Override
    public QfTenant add(QfTenant type) {
        tenantMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = tenantMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfTenant update(QfTenant type) {
        tenantMapper.updateById(type);
        return type;
    }

    @Override
    public QfTenant selectById(String id) {
        QfTenant tenant = tenantMapper.selectById(id);
        return tenant;
    }

    @Override
    public Page<QfTenant> list(TenantDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfTenant> page = tenantMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfTenant> all(TenantDto dto) {
        List<QfTenant> list = tenantMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfTenant> fuzzyQuery(TenantDto dto) {
        LambdaQueryWrapper<QfTenant> queryWrapper = Wrappers.<QfTenant>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfTenant::getName, dto.getName());
            queryWrapper.like(StrUtil.isNotBlank(dto.getCompanyName()), QfTenant::getCompanyName, dto.getCompanyName());
            queryWrapper.eq(dto.getEnable() != null, QfTenant::getEnable, dto.getEnable());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getCompanyCode()), QfTenant::getCompanyCode, dto.getCompanyCode());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getAccessKey()), QfTenant::getAccessKey, dto.getAccessKey());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getPhone()), QfTenant::getPhone, dto.getPhone());
            queryWrapper.eq(dto.getAuditStatus() != null, QfTenant::getAuditStatus, dto.getAuditStatus());
        }
        return queryWrapper;
    }

}
