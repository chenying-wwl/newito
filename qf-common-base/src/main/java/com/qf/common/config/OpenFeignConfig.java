package com.qf.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign开启日志
 *   1、配置文件 application.yml中
 *      feign:
 *          client:
 *          config:
 *              qf-order:  #对应微服务的名称
 *              loggerLevel: FULL
 *
 *    请注意！请注意！请注意！
 *    feign出于性能考虑,默认是没有日志的,需要手动开启.
 *
 * @author : 千锋健哥
 */
//注意,这个配置了@Configuration之后就是全局的bean了,就需要成了全局配置了
@Configuration
public class OpenFeignConfig {

    /**
     * 日志级别
     * 通过源码可以看到日志等级有 4 种，分别是：
     *     NONE：不输出日志。
     *     BASIC：只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
     *     HEADERS：将 BASIC 信息和请求头信息输出。
     *     FULL：输出完整的请求信息。
     */


    /**
     *  FULL级别
     * 输出完整的请求信息。
     * 当feign进行远程调用的时候控制台输出
     * ----
     * 2022-01-12 20:41:18.168 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] ---> GET http://mall-order/order/findOrderByUserId/1 HTTP/1.1
     * 2022-01-12 20:41:18.169 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Accept-Encoding: gzip
     * 2022-01-12 20:41:18.169 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Accept-Encoding: deflate
     * 2022-01-12 20:41:18.169 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Authorization: f4ff1ace-8aa3-4d6e-974f-83c60821210d
     * 2022-01-12 20:41:18.169 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] ---> END HTTP (0-byte body)
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] <--- HTTP/1.1 200 (45ms)
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] connection: keep-alive
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] content-type: application/json
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] date: Wed, 12 Jan 2022 12:41:18 GMT
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] keep-alive: timeout=60
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] transfer-encoding: chunked
     * 2022-01-12 20:41:18.216 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId]
     * 2022-01-12 20:41:18.217 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] {"msg":"success","code":0,"orders":[{"id":1,"userId":"1","commodityCode":"1","count":1,"amount":1}]}
     * 2022-01-12 20:41:18.217 DEBUG 30232 --- [nio-8055-exec-2] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] <--- END HTTP (100-byte body)
     * @return
     */
    @Bean
    Logger.Level feignFULLLevel(){
        return Logger.Level.FULL;
    }

    /**
     * BASIC级别
     * 只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
     * ----------------
     * 当feign进行远程调用的时候控制台输出:
     * 2022-01-12 20:39:20.537 DEBUG 30012 --- [nio-8055-exec-1] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] ---> GET http://mall-order/order/findOrderByUserId/1 HTTP/1.1
     * 2022-01-12 20:39:20.638 DEBUG 30012 --- [nio-8055-exec-1] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] <--- HTTP/1.1 200 (80ms)
     * @return
     */
/*    @Bean
    public Logger.Level feignBASICLevel() {
        return Logger.Level.BASIC;
    }*/

    /**
     * HEADERS
     * 将 BASIC 信息和请求头信息输出。
     * -------------
     * 当feign进行远程调用的时候控制台输出
     * 2022-01-12 20:40:37.499 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] ---> GET http://mall-order/order/findOrderByUserId/1 HTTP/1.1
     * 2022-01-12 20:40:37.500 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Accept-Encoding: gzip
     * 2022-01-12 20:40:37.500 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Accept-Encoding: deflate
     * 2022-01-12 20:40:37.500 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] Authorization: 563116cd-7124-4564-9ac5-1c740e8d70ec
     * 2022-01-12 20:40:37.500 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] ---> END HTTP (0-byte body)
     * 2022-01-12 20:40:37.553 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] <--- HTTP/1.1 200 (50ms)
     * 2022-01-12 20:40:37.553 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] connection: keep-alive
     * 2022-01-12 20:40:37.553 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] content-type: application/json
     * 2022-01-12 20:40:37.554 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] date: Wed, 12 Jan 2022 12:40:37 GMT
     * 2022-01-12 20:40:37.554 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] keep-alive: timeout=60
     * 2022-01-12 20:40:37.554 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] transfer-encoding: chunked
     * 2022-01-12 20:40:37.554 DEBUG 27368 --- [nio-8055-exec-5] c.t.m.feigndemo.feign.OrderFeignService  : [OrderFeignService#findOrderByUserId] <--- END HTTP (100-byte body)
     * @return
     */
/*    @Bean
    public Logger.Level feignHEADERSLevel() {
        return Logger.Level.HEADERS;
    }*/


}
