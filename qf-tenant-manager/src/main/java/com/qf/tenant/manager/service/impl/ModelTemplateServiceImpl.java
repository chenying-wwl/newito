package com.qf.tenant.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.dto.DeviceModelDto;
import com.qf.common.dto.ModelTemplateDto;
import com.qf.common.model.QfModelTemplate;
import com.qf.tenant.manager.feign.ModelTemplateFeign;
import com.qf.tenant.manager.service.ModelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelTemplateServiceImpl implements ModelTemplateService {

    @Autowired
    private ModelTemplateFeign modelTemplateFeign;

    @Override
    public R listModelTemplateByDeviceType(Long deviceTypeId) {
        ModelTemplateDto modelTemplateDto = new ModelTemplateDto();
        modelTemplateDto.setDeviceTypeId(deviceTypeId);
        R<List> r = modelTemplateFeign.findAll(modelTemplateDto);
        return r;
    }
}
