package com.ruoyi.guagua.bloomFilter;
import com.ruoyi.guagua.service.ISeckillProductService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class BloomFilterInitService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ISeckillProductService seckillProductService;  // 假设有个商品服务能拿到所有秒杀商品ID

    private static final String BLOOM_FILTER_NAME = "seckill:bloomfilter:products";

    @PostConstruct
    public void init() {
        RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter(BLOOM_FILTER_NAME);
        // 预计存储100000个元素，误判率1%
        bloomFilter.tryInit(100000L, 0.01);
        List<Long> productIds = seckillProductService.getSeckillProductIds();
        log.info("productIds:",productIds.toString());
        for (Long productId : productIds) {
            bloomFilter.add(productId);
        }
    }
}