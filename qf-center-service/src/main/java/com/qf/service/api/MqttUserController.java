package com.qf.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.DeviceDto;
import com.qf.common.dto.MqttUserDto;
import com.qf.common.model.MqttUser;
import com.qf.common.model.QfDevice;
import com.qf.service.service.MqttUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Mqtt服务器用户管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/mqttuser")
@Tag(name = "MqttUserController", description = "Mqtt服务器用户管理")
public class MqttUserController {

    @Autowired
    private MqttUserService mqttUserService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "Mqtt服务器用户接口 - 查询所有",description = "根据条件查询所有设备")
    public R<List> findAreaAll(@RequestBody(required = false) MqttUserDto dto) {
        List<MqttUser> list = mqttUserService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Mqtt服务器用户接口 - 根据id查询",description = "根据ID查询")
    public R<MqttUser> selectById(@NotNull @PathVariable(value = "id") String id) {
        MqttUser user = mqttUserService.selectById(id);
        return R.ok(user);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "Mqtt服务器用户接口 - 分页查询",description = "分页查询")
    R<Page<MqttUser>> list(@RequestBody(required = false) MqttUserDto dto) {
        Page<MqttUser> page = mqttUserService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "Mqtt服务器用户接口 - 新增",description = "新增")
    public R<MqttUser> add(@RequestBody MqttUser mqttUser) {
        MqttUser add = mqttUserService.add(mqttUser);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "Mqtt服务器用户接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = mqttUserService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "Mqtt服务器用户接口 - 修改",description = "修改")
    public R<MqttUser> update(@RequestBody MqttUser user) {
        MqttUser update = mqttUserService.update(user);
        return R.ok(update);
    }
}
