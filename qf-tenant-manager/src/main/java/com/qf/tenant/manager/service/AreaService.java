package com.qf.tenant.manager.service;

import com.qf.common.bean.R;
import com.qf.common.model.QfArea;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 千锋健哥
 */
public interface AreaService {

    /**
     * 根据父id查询所属行政区列表
     * @param parentId
     * @return
     */
    public R<List> findByParentId(Integer parentId);
}
