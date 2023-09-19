package com.qf.user.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfUser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 千锋健哥
 */
public interface UserService {

    /**
     * 根据条件查询所有
     * @return
     */
    public R<List> findAreaAll(UserDto dto);

    /**
     * 根据 ID 查询
     */
    public R<QfUser> selectById(String id);

    /**
     * 分页查询
     */
    R<Page<QfUser>> list(UserDto dto);

    /**
     * 新增
     */
    public R<QfUser> add(QfUser area);

    /**
     * 删除
     */
    public R<Boolean> delete(String id);

    /**
     * 修改
     */
    public R<QfUser> update(QfUser area);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public QfUser getUserByUserName(String userName);

    /**
     * 根据用户名获取用户的菜单权限集合
     * @param userName
     * @return
     */
    public List<String> findUserMenuByUserName(String userName);
}
