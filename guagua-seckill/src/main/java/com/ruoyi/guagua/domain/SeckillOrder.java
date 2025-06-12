package com.ruoyi.guagua.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long productId;
    private BigDecimal seckillPrice;
    private Date createTime;
    private Date payTime;
    private Integer status; // 0-未支付，1-已支付
}
