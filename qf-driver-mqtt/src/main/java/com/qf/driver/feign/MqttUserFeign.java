package com.qf.driver.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.model.MqttUser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mqtt服务器连接用户管理
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface MqttUserFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/mqttuser/all")
    public R<List> findAreaAll(@RequestBody(required = false) MqttUserDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/mqttuser/id/{id}")
    public R<MqttUser> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/mqttuser/list")
    R<Page<MqttUser>> list(@RequestBody(required = false) MqttUserDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/mqttuser/add")
    public R<MqttUser> add(@RequestBody MqttUser mqttUser);

    /**
     * 删除
     */
    @PostMapping("/service/mqttuser/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/mqttuser/update")
    public R<MqttUser> update(@RequestBody MqttUser user);
}
