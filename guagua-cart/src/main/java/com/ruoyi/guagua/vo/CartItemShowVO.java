package com.ruoyi.guagua.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartItemShowVO {
//    @TableId(type = IdType.AUTO)
    private Long id;

//    private Long userId;

    /** 商品名称 */
    private String name;

    /** 商品主图URL */
    private String image;

    private String description;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;



}
