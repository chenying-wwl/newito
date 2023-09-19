package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.IndustryDto;
import com.qf.common.model.QfIndustry;
import com.qf.user.manager.feign.IndustryFeign;
import com.qf.user.manager.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryFeign industryFeign;

    @Override
    public R<List> findAreaAll(IndustryDto dto) {
        R<List> result = industryFeign.findAreaAll(dto);
        return result;
    }

    @Override
    public R<QfIndustry> selectById(String id) {
        R<QfIndustry> result = industryFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfIndustry>> list(IndustryDto dto) {
        R<Page<QfIndustry>> result = industryFeign.list(dto);
        return result;
    }

    @Override
    public R<QfIndustry> add(QfIndustry area) {
        area.setCreateTime(new Date());
        R<QfIndustry> result = industryFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = industryFeign.delete(id);
        return result;
    }

    @Override
    public R<QfIndustry> update(QfIndustry area) {
        R<QfIndustry> result = industryFeign.update(area);
        return result;
    }

}
