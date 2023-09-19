package com.qf.user.manager.api;

import com.qf.common.bean.R;
import com.qf.common.model.QfUser;
import com.qf.common.utils.JwtUtil;
import com.qf.user.manager.bean.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

/**
 * 管理员认证授权
 * @author 千锋健哥
 */
@Controller
@RequestMapping("/user/auth")
@Tag(name = "UserAuthController", description = "管理员认证授权")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 认证成功后，返回jwt，将用户信息存储到redis中，以后的请求头携带该jwt
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    @Operation(summary = "管理员用户认证 - 登录",description = "登录认证")
    public R<String> login(@RequestBody(required=false) QfUser qfUser) {
        if (qfUser == null) {
            return R.fail("用户名或密码不能为空!");
        }
        if(StringUtils.isEmpty(qfUser.getUserName())
                || StringUtils.isEmpty(qfUser.getPassword())) {
            return R.fail("用户名或密码不能为空!");
        }

        //1. 对用户进行认证该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(qfUser.getUserName(), qfUser.getPassword()));
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("认证失败");
        }

        //2. 取到用户userID
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userName = loginUser.getQfUser().getUserName().toString();

        //3. 根据用户Id生成JWT
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), userName, null);
        //authenticate存入redis
        //redisCache.setCacheObject("login:"+userId, loginUser);
        //设置过期时间
        //redisCache.expire("login:"+userId, 1800);
        return R.ok(jwt);
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/doLogout")
    @ResponseBody
    @Operation(summary = "管理员用户认证 - 退出",description = "管理员退出")
    public R<Boolean> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getQfUser().getId();
        //redisCache.deleteObject("login:"+userid);
        return R.ok("退出成功!");
    }

}
