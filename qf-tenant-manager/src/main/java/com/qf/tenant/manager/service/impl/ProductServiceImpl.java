package com.qf.tenant.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import com.qf.common.utils.IdWorker;
import com.qf.common.utils.QfUtil;
import com.qf.tenant.manager.feign.ProductFeign;
import com.qf.tenant.manager.service.ProductService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductFeign productFeign;

    @Override
    public R<List<QfProduct>> findAreaAll() {
        ProductDto dto = new ProductDto();
        R<List<QfProduct>> result = productFeign.findAll(dto);
        return result;
    }

    /**
     * 添加产品
     * @param productDto
     * @return
     */
    @Override
    public R<QfProduct> saveProduct(ProductDto productDto) {
        //1.生成产品标识
        String productKey = "product_"+productDto.getAreaCode()+ "_" +new IdWorker().nextId();
        productDto.setProductKey(productKey);
        //2.产品认证用户名（与productKey保持一致）
        productDto.setUsername(productKey);
        //3.产品认证密码(默认123456)
        String pwd = QfUtil.md5("123456");
        //4.设置创建时间、修改时间、逻辑删除状态
        productDto.setCreateTime(new Date());
        productDto.setUpdateTime(new Date());
        productDto.setDeleted(0);
        productDto.setPassword(pwd);
        R<QfProduct> r = productFeign.add(productDto);
        return r;
    }

    @Override
    public R listByTenantId(ProductDto productDto) {
        R<Page<QfProduct>> r = productFeign.list(productDto);
        return r;
    }

    @Override
    public R listByTenantId(String tenantId) {
        ProductDto productDto = new ProductDto();
        productDto.setTenantId(tenantId);
        R<List<QfProduct>> r = productFeign.findAll(productDto);
        return r;
    }

    @Override
    public R getByProductKey(String productKey) {
        ProductDto productDto = new ProductDto();
        productDto.setProductKey(productKey);
        R<List<QfProduct>> r = productFeign.findAll(productDto);
        if (r.isOk()){
            return R.ok(r.getData().get(0));
        }else{
            return R.fail();
        }
    }

    @Override
    public R<QfProduct> selectById(String id) {
        R<QfProduct> product = productFeign.selectById(id);
        return product;
    }

}
