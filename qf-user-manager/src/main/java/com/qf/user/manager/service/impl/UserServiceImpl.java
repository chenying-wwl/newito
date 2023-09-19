package com.qf.user.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.UserDto;
import com.qf.common.model.QfUser;
import com.qf.user.manager.feign.UserFeign;
import com.qf.user.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public R<List> findAreaAll(UserDto dto) {
        R<List> result = userFeign.findUserAll(dto);
        return result;
    }

    @Override
    public R<QfUser> selectById(String id) {
        R<QfUser> result = userFeign.selectById(id);
        return result;
    }

    @Override
    public R<Page<QfUser>> list(UserDto dto) {
        R<Page<QfUser>> result = userFeign.list(dto);
        return result;
    }

    @Override
    public R<QfUser> add(QfUser area) {
        R<QfUser> result = userFeign.add(area);
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = userFeign.delete(id);
        return result;
    }

    @Override
    public R<QfUser> update(QfUser area) {
        R<QfUser> result = userFeign.update(area);
        return result;
    }

    @Override
    public QfUser getUserByUserName(String userName) {
        R<QfUser> result = userFeign.findUserByUserName(userName);
        return result.getData();
    }

    @Override
    public List<String> findUserMenuByUserName(String userName) {
        R<List<String>> list = userFeign.findUserMenuByUserName(userName);
        return list.getData();
    }

}
