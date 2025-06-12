package com.ruoyi.guagua.service.impl;

import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.domain.SeckillOrder;

import com.ruoyi.guagua.mapper.SeckillOrderMapper;
import com.ruoyi.guagua.mapper.SeckillProductMapper;
import com.ruoyi.guagua.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    SeckillProductMapper seckillProductMapper;
    @Autowired
    SeckillOrderMapper seckillOrderMapper;
    @Override
    public void createOrderAsync(Long userId, Long productId) {
        SeckillProduct product = seckillProductMapper.selectSeckillProductById(productId);
        if (product == null || product.getAvailableStock() <= 0) {
            return;
        }

        // 减库存
        int result = seckillProductMapper.reduceStock(productId);
        if (result <= 0) {
            return;
        }

        // 创建订单
        SeckillOrder order = new SeckillOrder();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setSeckillPrice(product.getSeckillPrice());
        order.setCreateTime(new Date());
        order.setStatus(0);
        seckillOrderMapper.insertOrder(order);
    }

}
