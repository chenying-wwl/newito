package com.qf.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.AreaDto;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfUser;
import com.qf.service.service.IQfUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员用户表 前端控制器
 *
 * @author 千锋健哥
 */
@RestController
@RequestMapping("/service/user")
@Tag(name = "QfUserController", description = "管理员用户管理")
public class QfUserController {

    @Autowired
    private IQfUserService userService;

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/all")
    @Operation(summary = "管理员用户接口 - 查询所有",description = "根据条件查询所有管理员用户")
    public R<List> findAreaAll(@RequestBody(required = false) UserDto dto) {
        List<QfUser> list = userService.all(dto);
        return R.ok(list);
    }

    /**
     * 根据 ID 查询
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "管理员用户接口 - 根据id查询",description = "根据ID查询")
    public R<QfUser> selectById(@NotNull @PathVariable(value = "id") String id) {
        QfUser area = userService.selectById(id);
        return R.ok(area);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "管理员用户接口 - 分页查询",description = "分页查询")
    R<Page<QfUser>> list(@RequestBody(required = false) UserDto dto) {
        Page<QfUser> page = userService.list(dto);
        return R.ok(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @Operation(summary = "管理员用户接口 - 新增",description = "新增")
    public R<QfUser> add(@RequestBody  QfUser area) {
        QfUser add = userService.add(area);
        return R.ok(add);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "管理员用户接口 - 删除",description = "根据id删除")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        boolean delete = userService.delete(id);
        return R.ok(delete);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Operation(summary = "管理员用户接口 - 修改",description = "修改")
    public R<QfUser> update(@RequestBody QfUser area) {
        QfUser update = userService.update(area);
        return R.ok(update);
    }

    /**
     * 根据用户名查询用户对象
     * @param userName
     * @return
     */
    @PostMapping("/username/{username}")
    @Operation(summary = "管理员用户接口 - 根据用户名查询用户",description = "根据用户名查询用户对象")
    public R<QfUser> findUserByUserName(@PathVariable(value = "username") String userName) {
        QfUser qfUser = userService.getUserByUserName(userName);
        return R.ok(qfUser);
    }

    /**
     * 根据用户名获取用户的菜单权限集合
     * @param userName
     * @return
     */
    @PostMapping("/menu/{username}")
    @Operation(summary = "管理员用户接口 - 根据用户名查询菜单",description = "根据用户名获取用户的菜单权限集合")
    public R<List<String>> findUserMenuByUserName(@PathVariable(value = "username") String userName){
        List<String> list = userService.getUserMenuByUserName(userName);
        return R.ok(list);
    }
}
