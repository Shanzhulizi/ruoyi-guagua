package com.ruoyi.guagua.mq;



import com.ruoyi.guagua.domain.SeckillMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeckillProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSeckillMessage(SeckillMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.SECKILL_EXCHANGE,
                RabbitMQConfig.SECKILL_ROUTING_KEY,
                message
        );
    }
}
