package com.ruoyi.guagua.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HotProductVO {

    /** 秒杀商品主键ID */
    private Long id;

    /** 商品ID（关联商品表） */
    @Excel(name = "商品ID", readConverterExp = "关=联商品表")
    private Long productId;

    /** 商品名 */
    private String name;

    private String image;

    /** 原价 */
    @Excel(name = "原价")
    private BigDecimal originalPrice;

    /** 秒杀价 */
    @Excel(name = "秒杀价")
    private BigDecimal seckillPrice;

    /** 秒杀开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "秒杀开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;


}
