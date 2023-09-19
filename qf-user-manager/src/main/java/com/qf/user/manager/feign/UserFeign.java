package com.qf.user.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfUser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 千锋健哥
 */
@FeignClient(name = "qf-center-service")
public interface UserFeign {

    /**
     * 根据条件查询所有
     * @return
     */
    @PostMapping("/service/user/all")
    public R<List> findUserAll(@RequestBody(required = false) UserDto dto);

    /**
     * 根据 ID 查询
     */
    @GetMapping("/service/user/id/{id}")
    public R<QfUser> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询
     */
    @PostMapping("/service/user/list")
    R<Page<QfUser>> list(@RequestBody(required = false) UserDto dto);

    /**
     * 新增
     */
    @PostMapping("/service/user/add")
    public R<QfUser> add(@RequestBody  QfUser area);

    /**
     * 删除
     */
    @PostMapping("/service/user/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改
     */
    @PostMapping("/service/user/update")
    public R<QfUser> update(@RequestBody QfUser area);

    /**
     * 根据用户名查询用户对象
     * @param userName
     * @return
     */
    @PostMapping("/service/user/username/{username}")
    public R<QfUser> findUserByUserName(@PathVariable(value = "username") String userName);

    /**
     * 根据用户名获取用户的菜单权限集合
     * @param userName
     * @return
     */
    @PostMapping("/service/user/menu/{username}")
    @Operation(summary = "管理员用户接口 - 根据用户名查询菜单",description = "根据用户名获取用户的菜单权限集合")
    public R<List<String>> findUserMenuByUserName(@PathVariable(value = "username") String userName);
}
