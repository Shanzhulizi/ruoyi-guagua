package com.ruoyi.guagua.mq;


import com.ruoyi.guagua.domain.SeckillMessage;
import com.ruoyi.guagua.service.ISeckillProductService;
import com.ruoyi.guagua.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class SeckillConsumer {

    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    ISeckillProductService seckillProductService;
    @RabbitListener(queues = "seckill.queue")
    public void receiveMessage(SeckillMessage message) {
        Long userId = message.getUserId();
        Long productId = message.getProductId();

        // 调用下单方法
//        seckillOrderService.createOrderAsync(userId, productId);
        // 这里调用原本的业务逻辑方法

        seckillProductService.purchaseSeckillProduct(productId, userId);
    }
}

