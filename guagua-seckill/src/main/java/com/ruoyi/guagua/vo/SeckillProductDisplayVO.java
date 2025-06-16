package com.ruoyi.guagua.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillProductDisplayVO {


    /**
     * 秒杀商品主键ID
     */
    private Long id;

//TODO
    //补充productId的逻辑，来使得前端购买页面能复用
    private Long productId;
    /**
     * 商品名
     */
    private String name;

    private String image;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;



}
