package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import com.qf.tenant.manager.feign.AreaFeign;
import com.qf.tenant.manager.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaFeign areaFeign;

    @Override
    public R<List> findByParentId(Integer parentId) {
        AreaDto dto = new AreaDto();
        dto.setPid(parentId);
        R<List> result = areaFeign.findAreaAll(dto);
        return result;
    }

}
