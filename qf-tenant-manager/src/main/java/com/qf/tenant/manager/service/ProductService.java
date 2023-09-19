package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有
     * @return
     */
    public R<List<QfProduct>> findAreaAll();

    /**
     * 保存产品信息
     * @param productDto
     * @return
     */
    public R saveProduct(ProductDto productDto);

    /**
     * 根据租户id查询产品列表(分页)
     */
    public R listByTenantId(ProductDto productDto);

    /**
     * 根据租户id查询产品列表（无分页）
     */
    public R listByTenantId(String tenantId);

    /**
     * 根据产品productKey查询详情
     * @param productKey
     * @return
     */
    R getByProductKey(String productKey);

    R<QfProduct> selectById(String valueOf);
}
