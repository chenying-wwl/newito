package com.qf.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.common.base.Service;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfUser;

import java.util.List;

/**
 * 管理员用户表服务
 *
 * @author 千锋健哥
 */
public interface IQfUserService extends Service<QfUser, UserDto> {

    /**
     * 根据用户名获取用户对象
     * @param userName
     * @return
     */
    public QfUser getUserByUserName(String userName);

    /**
     * 根据用户名获取用户的菜单权限集合
     * @param userName
     * @return
     */
    public List<String> getUserMenuByUserName(String userName);
}
