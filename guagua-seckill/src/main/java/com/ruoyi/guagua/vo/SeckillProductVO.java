package com.ruoyi.guagua.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data

public class SeckillProductVO {


    /**
     * 秒杀商品主键ID
     */
    private Long id;

    /**
     * 秒杀活动ID（支持多活动）
     */
    private Long activityId;

    /**
     * 商品ID（关联商品表）
     */
    private Long productId;

    /** 商品名 */
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

    /**
     * 秒杀库存总量
     */
    private Integer totalStock;

    /**
     * 当前剩余库存（预减库存用）
     */
    private Integer availableStock;

//    /**
//     * 已售数量（方便统计）
//     */
//    private Integer soldCount;

    /**
     * 每个用户限购数量
     */
    private Integer limitPerUser;

    /**
     * 秒杀开始时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;



    /**
     * 状态（0=未启用，1=启用，2=结束）
     */
    private Long status;

//    /**
//     * 逻辑删除标志（1=删除）
//     */
//    private Long isDeleted;
}
