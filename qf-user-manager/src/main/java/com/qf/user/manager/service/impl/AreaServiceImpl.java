package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;
import com.qf.user.manager.feign.AreaFeign;
import com.qf.user.manager.service.AreaService;
import org.checkerframework.checker.units.qual.Area;
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
    public R<List> findAreaAll(AreaDto dto) {
        R<List> list = areaFeign.findAreaAll(dto);
        return list;
    }

    @Override
    public R<QfArea> selectById(String id) {
        R<QfArea> result = areaFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfArea>> list(AreaDto dto) {
        R<Page<QfArea>> list = areaFeign.list(dto);
        return list;
    }

    @Override
    public R<QfArea> add(QfArea area) {
        R<QfArea> add = areaFeign.add(area);
        return add;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> delete = areaFeign.delete(id);
        return delete;
    }

    @Override
    public R<QfArea> update(QfArea area) {
        R<QfArea> update = areaFeign.update(area);
        return update;
    }

}
