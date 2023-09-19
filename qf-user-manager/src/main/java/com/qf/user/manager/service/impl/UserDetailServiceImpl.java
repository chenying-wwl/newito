package com.qf.user.manager.service.impl;

import com.qf.common.bean.R;
import com.qf.common.model.QfUser;
import com.qf.user.manager.bean.LoginUser;
import com.qf.user.manager.service.RoleService;
import com.qf.user.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义SpringSecurity用户登录授权逻辑
 * @author 千锋健哥
 */
@Service(value = "userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QfUser user = userService.getUserByUserName(username);
        if (user == null) {
            //用户不存在
            throw new UsernameNotFoundException("用户名不存在!");
        } else {
            //查找角色，实际应该查询权限，但我数据库没有设计所以就查角色就好了
            List<String> menuList = userService.findUserMenuByUserName(username);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String menu : menuList) {
                authorities.add(new SimpleGrantedAuthority(menu));
            }
            System.out.println("AuthUserDetailsService实现类loadUserByUsername方法user对象 ===> " + user);
            return new LoginUser(user, authorities);
        }
    }

    /**
     * 判断是否具有权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails=(UserDetails)principal;

            /**
             * 该方法主要对比认证过的用户是否具有请求URL的权限，有则返回true
             */
            //本次要访问的资源
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(request.getRequestURI());

            //用户拥有的权限中是否包含请求的url
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }

        return false;
    }


}
