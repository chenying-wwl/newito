package com.qf.user.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.RoleDto;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfUser;
import com.qf.user.manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员用户管理
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/user/manager")
@Tag(name = "UserController", description = "管理员用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "管理员用户接口 - 查询所有",description = "根据条件查询所有管理员用户")
    public R<List> findAreaAll(@RequestBody(required = false) UserDto dto) {
        if (dto == null) {
            dto = new UserDto();
        }
        R<List> result = userService.findAreaAll(dto);
        return result;
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "管理员用户接口 - 根据id查询",description = "根据ID查询")
    public R<QfUser> selectById(@NotNull @PathVariable(value = "id") String id) {
        R<QfUser> result = userService.selectById(id);
        return result;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "管理员用户接口 - 分页查询",description = "分页查询")
    R<Page<QfUser>> list(@RequestBody(required = false) UserDto dto) {
        if (dto == null) {
            dto = new UserDto();
        }
        R<Page<QfUser>> result = userService.list(dto);
        return result;
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "管理员用户接口 - 新增",description = "新增")
    public R<QfUser> add(@RequestBody  QfUser area) {
        R<QfUser> result = userService.add(area);
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "管理员用户接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        R<Boolean> result = userService.delete(id);
        return result;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "管理员用户接口 - 修改",description = "修改")
    public R<QfUser> update(@RequestBody QfUser area) {
        R<QfUser> result = userService.update(area);
        return result;
    }
}
