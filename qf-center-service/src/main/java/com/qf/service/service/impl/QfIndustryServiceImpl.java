package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.IndustryDto;
import com.qf.common.mapper.QfIndustryMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfIndustry;
import com.qf.service.service.IQfIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行业表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfIndustryServiceImpl implements IQfIndustryService {

    @Autowired
    private QfIndustryMapper industryMapper;

    @Override
    public QfIndustry add(QfIndustry type) {
        industryMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = industryMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfIndustry update(QfIndustry type) {
        industryMapper.updateById(type);
        return type;
    }

    @Override
    public QfIndustry selectById(String id) {
        QfIndustry industry = industryMapper.selectById(Long.parseLong(id));
        return industry;
    }

    @Override
    public Page<QfIndustry> list(IndustryDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfIndustry> page = industryMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfIndustry> all(IndustryDto dto) {
        List<QfIndustry> list = industryMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfIndustry> fuzzyQuery(IndustryDto dto) {
        LambdaQueryWrapper<QfIndustry> queryWrapper = Wrappers.<QfIndustry>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getName()), QfIndustry::getName, dto.getName());
        }
        return queryWrapper;
    }

}
