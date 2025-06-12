package com.ruoyi.guagua.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.mapper.SeckillProductMapper;
import com.ruoyi.guagua.redis.util.RedisUtils;
import com.ruoyi.guagua.service.SeckillStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeckillStockServiceImpl implements SeckillStockService {
//
//    @Autowired
//    private SeckillProductMapper productMapper;
//
//    @Autowired
//    private RedisUtils redisUtils;
//
//    /**
//     * 预热商品库存到Redis
//     */
//    public void preheatStockToRedis(SeckillProduct product) {
//        String redisKey = getStockRedisKey(product.getId());
//
//        // 只在Redis中不存在时预热
//        if (redisUtils.getStock(redisKey) == null) {
//            // 从数据库获取最新库存
//            Integer stock = productMapper.selectStockById(product.getId());
//            if (stock != null) {
//                // 存入Redis
//                redisUtils.setStock(redisKey, stock);
//
//                // 设置过期时间（秒杀结束后）
//                long seconds = java.time.Duration.between(
//                        LocalDateTime.now(),
//                        product.getEndTime()
//                ).getSeconds();
//
//                if (seconds > 0) {
//                    redisUtils.expire(redisKey, seconds, java.util.concurrent.TimeUnit.SECONDS);
//                }
//            }
//        }
//    }
//
//    /**
//     * 批量预热商品库存
//     */
//    public void batchPreheatStock(List<SeckillProduct> products) {
//        if (products != null && !products.isEmpty()) {
//            for (SeckillProduct product : products) {
//                preheatStockToRedis(product);
//            }
//        }
//    }
//
//    /**
//     * 更新商品库存（数据库和Redis）
//     */
//    @Transactional
//    public AjaxResult updateStock(Long productId, Integer stock) {
//        // 更新数据库
//        int result = productMapper.updateStockById(productId, stock);
//
//        if (result > 0) {
//            // 更新Redis
//            String redisKey = getStockRedisKey(productId);
//            redisUtils.setStock(redisKey, stock);
//            return AjaxResult.success("库存更新成功");
//        }
//
//        return AjaxResult.error("库存更新失败");
//    }
//
//    /**
//     * 获取商品库存
//     */
//    public Integer getStock(Long productId) {
//        String redisKey = getStockRedisKey(productId);
//
//        // 先从Redis获取
//        Integer stock = redisUtils.getStock(redisKey);
//
//        // Redis没有则从数据库获取
//        if (stock == null) {
//            stock = productMapper.selectStockById(productId);
//            // 放入Redis
//            if (stock != null) {
//                redisUtils.setStock(redisKey, stock);
//            }
//        }
//
//        return stock;
//    }
//
//    /**
//     * 扣减库存
//     */
//    public Boolean decreaseStock(Long productId, Integer quantity) {
//        String redisKey = getStockRedisKey(productId);
//        return redisUtils.decreaseStock(redisKey, quantity);
//    }
//
//    /**
//     * 查询未来n小时内开始的秒杀商品
//     */
//    public List<SeckillProduct> findProductsStartingInHours(int hours) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime endTime = now.plusHours(hours);
//        return productMapper.selectProductsByTimeRange(now, endTime);
//    }
//
//    /**
//     * 获取库存Redis key
//     */
//    private String getStockRedisKey(Long productId) {
//        return "seckill:stock:" + productId;
//    }
}


