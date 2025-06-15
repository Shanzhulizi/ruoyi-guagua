package com.ruoyi.guagua.utils;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.mapper.SeckillProductMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.List;

//TODO
/**
 *
 * 这是一个预热的功能
 * 我在做电商秒杀模块时，用Redis，RabbitMQ和Lua脚本，速度有了很大提升，
 * 但是我发现，当我把线程数提升到10000，就出现了20%的错，而且，这些错误大部分出现在刚开始时
 * GPT猜测这是没有预热导致，但是我现在要抓紧做其他功能，所以，先凑合着吧
 *
 */
@Component
public class WarmupRunner  {

//    @PostMapping("/preheat")
//    public R preheat(@RequestParam Long productId) {
//        SeckillProduct product = seckillProductMapper.selectSeckillProductById(productId);
//        if (product == null) {
//            return R.error("商品不存在");
//        }
//
//        // 1. 初始化库存到 Redis
//        String stockKey = "seckill:stock:" + productId;
//        redisTemplate.opsForValue().set(stockKey, String.valueOf(product.getAvailableStock()));
//
//        // 2. 清空用户去重集合
//        String userSetKey = "seckill:users:" + productId;
//        redisTemplate.delete(userSetKey);
//
//        // 3. 清空用户日志（如果你之前压测过）
//        String logKey = "seckill:log:" + stockKey;
//        redisTemplate.delete(logKey);
//
//        return R.ok("预热成功，库存：" + product.getAvailableStock());
//    }

}
