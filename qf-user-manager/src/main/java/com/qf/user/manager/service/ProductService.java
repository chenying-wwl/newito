package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ProductDto;
import com.qf.common.model.QfProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品管理
 * @author 千锋健哥
 */
public interface ProductService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(ProductDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfProduct> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfProduct>> list(ProductDto dto);
}
