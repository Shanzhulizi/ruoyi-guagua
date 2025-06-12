package com.ruoyi.guagua.redis.util;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 设置库存
     */
    public void setStock(String key, Integer stock) {
        redisTemplate.opsForValue().set(key, stock);
    }

    /**
     * 获取库存
     */
    public Integer getStock(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : (Integer) value;
    }

    /**
     * 扣减库存
     */
    public Boolean decreaseStock(String key, Integer delta) {
        Long stock = redisTemplate.opsForValue().decrement(key, delta);
        return stock != null && stock >= 0;
    }

    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }
}
