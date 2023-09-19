package com.qf.service.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.common.bean.Pages;
import com.qf.common.dto.UserDto;
import com.qf.common.mapper.QfUserMapper;
import com.qf.common.model.QfArea;
import com.qf.common.model.QfUser;
import com.qf.service.service.IQfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员用户表 服务实现类
 *
 * @author 千锋健哥
 */
@Service
public class QfUserServiceImpl implements IQfUserService {

    @Autowired
    private QfUserMapper userMapper;

    @Override
    public QfUser add(QfUser type) {
        userMapper.insert(type);
        return type;
    }

    @Override
    public boolean delete(String id) {
        int i = userMapper.deleteById(id);
        return i>0;
    }

    @Override
    public QfUser update(QfUser type) {
        userMapper.updateById(type);
        return type;
    }

    @Override
    public QfUser selectById(String id) {
        QfUser user = userMapper.selectById(id);
        return user;
    }

    @Override
    public Page<QfUser> list(UserDto dto) {
        if (ObjectUtil.isNull(dto.getPage())) {
            dto.setPage(new Pages());
        }
        Page<QfUser> page = userMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));

        return page;
    }

    @Override
    public List<QfUser> all(UserDto dto) {
        List<QfUser> list = userMapper.selectList(fuzzyQuery(dto));
        return list;
    }

    @Override
    public LambdaQueryWrapper<QfUser> fuzzyQuery(UserDto dto) {
        LambdaQueryWrapper<QfUser> queryWrapper = Wrappers.<QfUser>query().lambda();
        if (ObjectUtil.isNotEmpty(dto)) {
            queryWrapper.like(StrUtil.isNotBlank(dto.getNickName()), QfUser::getNickName, dto.getNickName());
            queryWrapper.like(StrUtil.isNotBlank(dto.getRealName()), QfUser::getRealName, dto.getRealName());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getUserName()), QfUser::getUserName, dto.getUserName());
            queryWrapper.eq(StrUtil.isNotEmpty(dto.getEmail()), QfUser::getEmail, dto.getEmail());
            queryWrapper.eq(dto.getPhone() != null, QfUser::getPhone, dto.getPhone());
            queryWrapper.eq(dto.getEnable() != null, QfUser::getEnable, dto.getEnable());
            queryWrapper.eq(dto.getDeleted() != null, QfUser::getDeleted, dto.getDeleted());
        }
        return queryWrapper;
    }

    @Override
    public QfUser getUserByUserName(String userName) {
        LambdaQueryWrapper<QfUser> queryWrapper = Wrappers.<QfUser>query().lambda();

        queryWrapper.eq(StrUtil.isNotEmpty(userName), QfUser::getUserName, userName);
        QfUser qfUser = userMapper.selectOne(queryWrapper);
        return qfUser;
    }

    @Override
    public List<String> getUserMenuByUserName(String userName) {
        List<String> list = userMapper.findUserMenuByUserName(userName);
        return list;
    }



}
