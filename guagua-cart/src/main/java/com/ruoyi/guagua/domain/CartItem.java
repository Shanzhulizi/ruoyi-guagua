package com.ruoyi.guagua.domain;

import lombok.Data;

import java.util.Date;

@Data
//@TableName("cart_item")
public class CartItem {
//    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
}