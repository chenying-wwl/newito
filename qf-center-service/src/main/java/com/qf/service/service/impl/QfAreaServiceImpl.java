package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.AreaDto;
import com.qf.common.exception.DuplicateException;
import com.qf.common.exception.NotFoundException;
import com.qf.common.exception.ServiceException;
import com.qf.common.mapper.QfAreaMapper;
import com.qf.common.model.QfArea;
import com.qf.service.service.IQfAreaService;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 行政区划表 服务实现类
 * </p>
 *
 * @author 千锋健哥
 */
@Service
public class QfAreaServiceImpl implements IQfAreaService {

    @Autowired
    private QfAreaMapper areaMapper;

    @Override
    public QfArea add(QfArea area) {
        areaMapper.insert(area);
        return area;
    }

    @Override
    public boolean delete(String id) {
        int count = areaMapper.deleteById(id);
        return count > 0;
    }

    @Override
    public QfArea update(QfArea area) {
        areaMapper.updateById(area);
        return area;
    }

    @Override
    public QfArea selectById(String id) {
        QfArea area = areaMapper.selectById(id);
        return area;
    }

    @Override
    public Page<QfArea> list(AreaDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfArea> page = areaMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfArea> all(AreaDto dto) {
        List<QfArea> list = areaMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfArea> fuzzyQuery(AreaDto dto) {
        LambdaQueryWrapper<QfArea> queryWrapper = Wrappers.<QfArea>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(dto.getCode() != null, QfArea::getCode, dto.getCode());
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfArea::getName, dto.getName());
            queryWrapper.eq(dto.getLevel() != null, QfArea::getLevel, dto.getLevel());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getPname()), QfArea::getPname, dto.getPname());
            queryWrapper.eq(dto.getType() != null, QfArea::getType, dto.getType());
            queryWrapper.eq(dto.getPid() != null, QfArea::getPid, dto.getPid());
        }
        return queryWrapper;
    }

}
