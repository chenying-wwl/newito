package com.qf.user.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfModelTemplate;
import com.qf.user.manager.feign.ModelTemplateFeign;
import com.qf.user.manager.service.ModelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 千锋健哥
 */
@Service
public class ModelTemplateServiceImpl implements ModelTemplateService {

    @Autowired
    private ModelTemplateFeign modelTemplateFeign;

    @Override
    public R<Map<String, String>> findSelectAll() {
        ModelTemplateDto dto = new ModelTemplateDto();
        R<List> r = modelTemplateFeign.findAreaAll(dto);
        Map<String, String> resultMap = new HashMap<>();

        if (r.getData() != null && r.getData().size() > 0) {
            List<QfModelTemplate> modelList = BeanUtil.copyToList(r.getData(), QfModelTemplate.class);

            for (QfModelTemplate modelTemplate : modelList) {
                if (resultMap.get(modelTemplate.getPropertyName()) == null) {
                    resultMap.put(modelTemplate.getPropertyName(), modelTemplate.getDescription());
                }
            }
            return R.ok(resultMap);
        }
        return R.fail(new HashMap<>());
    }

    @Override
    public R<List> findAreaAll(ModelTemplateDto dto) {
        R<List> result = modelTemplateFeign.findAreaAll(dto);
        return result;
    }

    @Override
    public R<QfModelTemplate> selectById(String id) {
        R<QfModelTemplate> result = modelTemplateFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfModelTemplate>> list(ModelTemplateDto dto) {
        R<Page<QfModelTemplate>> result = modelTemplateFeign.list(dto);
        return result;
    }

    @Override
    public R<QfModelTemplate> add(QfModelTemplate area) {
        R<QfModelTemplate> result = modelTemplateFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = modelTemplateFeign.delete(id);
        return result;
    }

    @Override
    public R<QfModelTemplate> update(QfModelTemplate area) {
        R<QfModelTemplate> result = modelTemplateFeign.update(area);
        return result;
    }

}
