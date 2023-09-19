package com.qf.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.common.model.QfUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 管理员用户表 Mapper 接口
 * </p>
 *
 * @author 千锋健哥
 */
public interface QfUserMapper extends BaseMapper<QfUser> {

    /**
     * 根据用户名查询权限集合
     * @param userName
     * @return
     */
    @Select("select DISTINCT e.perms from qf_user a " +
            "LEFT JOIN qf_user_role b on a.id = b.user_id " +
            "LEFT JOIN qf_role c on b.role_id = c.id " +
            "LEFT JOIN qf_role_menu d on d.role_id = b.id " +
            "LEFT JOIN qf_menu e ON e.id = d.menu_id " +
            "where a.user_name = #{userName}")
    public List<String> findUserMenuByUserName(@Param("userName") String userName);
}
