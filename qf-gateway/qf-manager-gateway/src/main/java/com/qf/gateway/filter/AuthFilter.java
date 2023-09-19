package com.qf.gateway.filter;

import com.qf.gateway.service.TenantService;
import com.qf.gateway.utils.EncryptUtil;
import com.qf.gateway.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 租户以及第三方业务系统鉴权过滤器
 * @author 千锋健哥
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private TenantService tenantService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求对象, 响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2. 获取用户访问的url地址
        String path = request.getURI().getPath();

        /**
         * 3. 判断如果是租户登录放行,
         *      租户验证码放行,
         *      租户注册放行,
         *      租户退出放行
         *      管理员系统路径放行到user-manager服务进行认证
         */
        if (path.contains("/auth/tenant/login")
                || path.contains("/auth/checkcode/send")
                || path.contains("/auth/tenant/regist")
                || path.contains("/auth/tenant/isexist")
                || path.contains("/user/")) {
            //放行
            return chain.filter(exchange);
        }

        //4. 判断第三方业务系统访问认证逻辑
        if (path.contains("/api/")) {

            //4.1. 从请求头中获取key和秘钥还有签名
            //获取Key
            String accessKey = request.getHeaders().getFirst("access-key");
            //获取秘钥
            String accessSecurit = request.getHeaders().getFirst("access-securit");
            //获取签名
            String sign = request.getHeaders().getFirst("sign");

            //4.2. 处理第三方业务系统访问
            try {
                if (!StringUtils.isEmpty(accessKey)
                        && !StringUtils.isEmpty(accessSecurit)
                        && !StringUtils.isEmpty(sign)) {
                    //4.3. accessKey + accessSecurit组成签名
                    String signTemp = accessKey + accessSecurit;
                    //4.4. 校验签名是否正确
                    boolean valid = EncryptUtil.valid(signTemp, sign);
                    //4.5. 如果签名正确, 校验AccessKey和AccessSecurit
                    if (valid) {
                        Boolean flag = tenantService.findByAccessKey(accessKey, accessSecurit);
                        //4.6. 签名正确并且AccessKey和AccessSecurit都正确, 放行
                        if (flag) {
                            request.mutate().header("token", accessKey);
                            return chain.filter(exchange);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                //4.7. 第三方系统鉴权失败, 返回401权限不足提示
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }

        /**
         * 租户鉴权业务
         */
        //5. 从请求头中获取jwt令牌
        String token = request.getHeaders().getFirst("token");
        //6. 判断如果jwt令牌不为空校验Jwt
        if (!StringUtils.isEmpty(token)) {
            try {
                Claims claims = JwtUtil.parseJWT(token);
                //7. 解析后将用户的身份信息, 放入当前请求的请求头中, 放行
                String subject = claims.getSubject();
                request.mutate().header("token", subject);
                //放行
                return chain.filter(exchange);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        //8. 如果解析报错, 返回401权限不足提示信息
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();

    }

    @Override
    public int getOrder() {
        return 0;
    }

}
