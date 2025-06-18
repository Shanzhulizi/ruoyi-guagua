package com.ruoyi.guagua.redis;


import com.ruoyi.guagua.mapper.SeckillProductMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class RedisSeckillService {

    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    SeckillProductMapper seckillProductMapper;

    @Autowired
    private RedissonClient redissonClient;  // 配置见下方



    private DefaultRedisScript<Long> seckillScript;

    public RedisSeckillService() {
        seckillScript = new DefaultRedisScript<>();
        seckillScript.setLocation(new ClassPathResource("script/seckill.lua"));
        seckillScript.setResultType(Long.class);
    }

    /**
     * 执行秒杀逻辑
     *
     * @param productId 商品ID
     * @param userId    用户ID
     * @return 0=库存不足，1=成功，2=重复抢购
     */
    public long executeSeckill(Long productId, Long userId) {
////        String stockKey = "seckill:stock:" + productId;
////        String userKey = "seckill:user:" + productId;
////
////
////        return redisTemplate.execute(
////                seckillScript,
////                // KEYS
////                Arrays.asList(stockKey, userKey),
////                // ARGV
////                userId.toString()
////        );
//
//        String stockKey = "seckill:stock:" + productId;
//        String userKey = "seckill:user:" + productId;
//
//        // 提前判断库存避免无效 Lua 执行
////        String stockStr = (String) redisTemplate.opsForValue().get(stockKey);
////        if (stockStr == null || Integer.parseInt(stockStr) <= 0) {
////            return 0L;
////        }
//        Integer stock = (Integer) redisTemplate.opsForValue().get(stockKey);
//        //TODO
//        // 这里有问题，缓存没命中，应该去数据库查真实库存
////        if (stock == null || stock <= 0) {
////            return 0L;
////        }
//        if (stock == null) {
//            // 缓存没命中，回源数据库
//
//            Integer dbStock = seckillProductMapper.selectStockByProductId(productId);
//            /**
//             * 缓存穿透
//             */
////            if (stock == null || stock <= 0) {
////                // 库存不足或无数据
////                return 0L;
////            }
//
//            //为空直接在缓存设置为0，防止穿透
//            if (dbStock == null || dbStock <= 0) {
//                redisTemplate.opsForValue().set(stockKey, 0, 10, TimeUnit.MINUTES);
//                return 0L; // 库存无了
//            }
//
//            // 回写缓存，设置过期时间
//            redisTemplate.opsForValue().set(stockKey, dbStock, 10, TimeUnit.MINUTES);
//            stock = dbStock;
//        }
//
////        if (stock <= 0) {
////            return 0L; // 无库存
////        }
//
//
//        return redisTemplate.execute(
//                seckillScript,
//                Arrays.asList(stockKey, userKey),
//                userId.toString()
//        );


        String stockKey = "seckill:stock:" + productId;
        String userKey = "seckill:user:" + productId;
        String stockStr = (String) redisTemplate.opsForValue().get(stockKey);
        Integer stock = null;

        //分布式锁配置
        String lockKey = "lock:" + stockKey;

        int retryCount = 0;
        int maxRetry = 10;


        while (stockStr == null && retryCount < maxRetry) {
            RLock lock = redissonClient.getLock(lockKey);
            boolean locked = false;

            try {
                // 等待最多100ms，拿到后最多持有5秒
                locked = lock.tryLock(100, 5000, TimeUnit.MILLISECONDS);
                if (locked) {
                    // 二次检查，防止并发下缓存已被其他线程写入
                    stockStr = (String) redisTemplate.opsForValue().get(stockKey);
                    if (stockStr == null) {
                        Integer dbStock = seckillProductMapper.selectStockByProductId(productId);
                        if (dbStock == null || dbStock <= 0) {
                            redisTemplate.opsForValue().set(stockKey, "0", 10, TimeUnit.MINUTES); // 防止缓存穿透
                            return 0L;
                        }
                        redisTemplate.opsForValue().set(stockKey, dbStock.toString(), 10, TimeUnit.MINUTES);
                        stock = dbStock;
                    } else {
                        stock = Integer.parseInt(stockStr);
                    }
                    break; // 成功获取并写入缓存，退出自旋
                } else {
                    // 没获取到锁，等待后自旋重试
                    Thread.sleep(50); // 可扩展为指数退避
                    stockStr = (String) redisTemplate.opsForValue().get(stockKey);
                    if (stockStr != null) {
                        stock = Integer.parseInt(stockStr);
                        break; // 缓存刷新了，退出循环
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("线程中断", e);
            } finally {
                if (locked) {
                    lock.unlock(); // Redisson 自动处理线程绑定
                }
            }

            retryCount++;
        }

        // 超过最大重试次数还没有库存信息，判定为库存无数据
        if (stock == null && stockStr == null) {
            return 0L;
        }

        // 缓存命中但库存为0，直接返回
        if (stock == null) {
            stock = Integer.parseInt(stockStr);
        }
        if (stock <= 0) return 0L;

        // 执行 Lua 脚本，原子扣库存 + 用户去重
        return redisTemplate.execute(
                seckillScript,
                Arrays.asList(stockKey, userKey),
                userId.toString()
        );
    }
}

