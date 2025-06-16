package com.ruoyi.guagua.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CartItemDTO {

    private Long userId;

    private Long productId;

    private Integer quantity;


}
