package com.qf.user.manager.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.qf.common.model.QfUser;
import com.qf.common.utils.JsonUtil;
import com.qf.common.utils.JwtUtil;
import com.qf.user.manager.bean.LoginUser;
import com.qf.user.manager.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 认证过滤器
 * 对请求头中的token进行解析，
 * 将解析到的userId到redis中进行查询，
 * 将查询到的LoginUser封装成Authentication对象存入SecurityContextHolder
 * @author 千锋健哥
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 获取请求头中携带token的key也就是名字
     */
    @Value("${jwt.token-header}")
    private String header;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        //1. 获取用户访问的url地址
        String path = request.getRequestURI();
        if (path.contains("/user/auth")) {
            //如果是登录路径放行
            chain.doFilter(request, response);
            return;
        }

        //2. 获取请求头中的token
        String token = request.getHeader(header);
        if (!StrUtil.isEmpty(token)) {
            String userName = null;
            try {
                //3. 解析jwt
                Claims claims = JwtUtil.parseJWT(token);
                userName = claims.getSubject();
            } catch (Exception e) {
                //e.printStackTrace();
                WriteJSON(request,response, new AccessDeniedException("非法Token，请重新登陆!"));
                return;
            }

            //4. 根据用户名获取用户对象
            QfUser qfUser = userService.getUserByUserName(userName);
            //5. 根据用户名获取菜单集合
            List<String> menuList = userService.findUserMenuByUserName(userName);
            //6. 将菜单转换为SimpleGrantedAuthority对象
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String menu : menuList) {
                authorities.add(new SimpleGrantedAuthority(menu));
            }
            //7. 将用户和菜单集合转换为UserDetails对象
            LoginUser loginUser = new LoginUser(qfUser, authorities);

            //8. 对比前端发送请求携带的的token是否与redis中存储的一致
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        chain.doFilter(request, response);
    }

    /**
     * json处理
     * @param request
     * @param response
     * @param obj
     * @throws IOException
     * @throws ServletException
     */
    private void WriteJSON(HttpServletRequest request,
                           HttpServletResponse response,
                           Object obj) throws IOException, ServletException {
        //这里很重要，否则页面获取不到正常的JSON数据集
        response.setContentType("application/json;charset=UTF-8");

        //跨域设置
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method", "POST,GET");
        //输出JSON
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.toJsonString(obj));
        out.flush();
        out.close();
    }


}
