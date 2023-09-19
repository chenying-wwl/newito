package com.qf.tenant.manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.common.bean.R;
import com.qf.common.dto.GroupDto;
import com.qf.common.model.QfGroup;
import com.qf.tenant.manager.feign.GroupFeign;
import com.qf.tenant.manager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupFeign groupFeign;

    @Override
    public R saveGroup(QfGroup qfGroup) {
        //1.检查当前分组名是否存在
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName(qfGroup.getGroupName());
        R<List> r = groupFeign.findAll(groupDto);
        if(r.isOk() && r.getData().size()>0){
            return R.fail("分组名称已经存在！");
        }else{
            //2.保存分组信息
            qfGroup.setCreateTime(new Date());
            qfGroup.setDeleted(0);
            return groupFeign.add(qfGroup);
        }
    }

    @Override
    public R listGroup(GroupDto groupDto) {
        R<Page<QfGroup>> r = groupFeign.list(groupDto);
        return r;
    }
}
