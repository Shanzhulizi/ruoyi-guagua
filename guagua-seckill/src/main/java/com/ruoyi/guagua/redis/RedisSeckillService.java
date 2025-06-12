package com.ruoyi.guagua.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RedisSeckillService {

    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate<Object, Object> redisTemplate;

    private DefaultRedisScript<Long> seckillScript;

    public RedisSeckillService() {
        seckillScript = new DefaultRedisScript<>();
        seckillScript.setLocation(new ClassPathResource("script/seckill.lua"));
        seckillScript.setResultType(Long.class);
    }

    /**
     * 执行秒杀逻辑
     * @param productId 商品ID
     * @param userId 用户ID
     * @return 0=库存不足，1=成功，2=重复抢购
     */
    public long executeSeckill(Long productId, Long userId) {
        String stockKey = "seckill:stock:" + productId;
        String userKey = "seckill:user:" + productId;

        return redisTemplate.execute(
                seckillScript,
                // KEYS
                Arrays.asList(stockKey, userKey),
                // ARGV
                userId.toString()
        );
    }
}

