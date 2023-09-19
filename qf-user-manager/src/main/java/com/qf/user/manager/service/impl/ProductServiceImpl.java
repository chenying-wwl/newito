package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import com.qf.user.manager.feign.ProductFeign;
import com.qf.user.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductFeign productFeign;

    @Override
    public R<List> findAreaAll(ProductDto dto) {
        R<List> list = productFeign.findAreaAll(dto);
        return list;
    }

    @Override
    public R<QfProduct> selectById(String id) {
        R<QfProduct> product = productFeign.selectById(id);
        return product;
    }

    @Override
    public R<Page<QfProduct>> list(ProductDto dto) {
        R<Page<QfProduct>> page = productFeign.list(dto);
        return page;
    }

}
