package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.RoleDto;
import com.qf.common.model.QfRole;
import com.qf.user.manager.feign.RoleFeign;
import com.qf.user.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleFeign roleFeign;

    @Override
    public R<List> findAreaAll(RoleDto dto) {
        R<List> result = roleFeign.findAreaAll(dto);
        return result;
    }

    @Override
    public R<QfRole> selectById(String id) {
        R<QfRole> result = roleFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfRole>> list(RoleDto dto) {
        R<Page<QfRole>> result = roleFeign.list(dto);
        return result;
    }

    @Override
    public R<QfRole> add(QfRole area) {
        R<QfRole> result = roleFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = roleFeign.delete(id);
        return result;
    }

    @Override
    public R<QfRole> update(QfRole area) {
        R<QfRole> result = roleFeign.update(area);
        return result;
    }

}
