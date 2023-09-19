package com.qf.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.common.base.Service;
import com.qf.common.dto.AreaDto;
import com.qf.common.model.QfArea;

import java.util.List;

/**
 * 行政区划表 服务类
 *
 * @author 千锋健哥
 */
public interface IQfAreaService extends Service<QfArea, AreaDto> {


}
