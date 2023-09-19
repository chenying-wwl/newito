package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.ProductDto;
import com.qf.common.mapper.QfProductMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfProduct;
import com.qf.service.service.IQfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfProductServiceImpl implements IQfProductService {

    @Autowired
    private QfProductMapper productMapper;

    @Override
    public QfProduct add(QfProduct type) {
        productMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = productMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfProduct update(QfProduct type) {
        productMapper.updateById(type);
        return type;
    }

    @Override
    public QfProduct selectById(String id) {
        QfProduct product = productMapper.selectById(id);
        return product;
    }

    @Override
    public Page<QfProduct> list(ProductDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfProduct> page = productMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfProduct> all(ProductDto dto) {
        List<QfProduct> list = productMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfProduct> fuzzyQuery(ProductDto dto) {
        LambdaQueryWrapper<QfProduct> queryWrapper = Wrappers.<QfProduct>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfProduct::getName, dto.getName());
            queryWrapper.like(StrUtil.isNotBlank(dto.getUsername()), QfProduct::getUsername, dto.getUsername());
            queryWrapper.eq(dto.getTenantId() != null, QfProduct::getTenantId, dto.getTenantId());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getProductKey()), QfProduct::getProductKey, dto.getProductKey());
            queryWrapper.eq(dto.getAreaCode() != null, QfProduct::getAreaCode, dto.getAreaCode());
        }
        return queryWrapper;
    }

}
