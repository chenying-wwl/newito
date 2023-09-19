import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@TestConfiguration
public class Test1 {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void test1(){
        redisTemplate.opsForValue().set("12345678900", "123456");
        System.out.println("置入成功");
    }
}
