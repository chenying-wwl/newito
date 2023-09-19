package com.qf.driver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.model.MqttUser;

import java.util.List;

/**
 * @author 千锋健哥
 */
public interface MqttUserService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(MqttUserDto dto);

    /**
     * 根据 ID 查询
     */
    public R<MqttUser> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<MqttUser>> list(MqttUserDto dto);

    /**
     * 新增
     */
    public R<MqttUser> add(MqttUser area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<MqttUser> update(MqttUser area);

    /**
     * 根据租户id创建mqtt服务器账户
     * @param tenantId
     */
    public void addMqttUser(String tenantId);
}
