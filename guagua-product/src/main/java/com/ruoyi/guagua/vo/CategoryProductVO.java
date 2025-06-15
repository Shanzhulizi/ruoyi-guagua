package com.ruoyi.guagua.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryProductVO {

    /** 商品ID */
    private Long id;

    /** 商品名称 */
    private String name;



    /** 品牌 */
    private String brand;

    /** 商品主图URL */
    private String image;


    private String description;

    /** 价格 */
    private BigDecimal price;


    /** 销量 */
    private Long salesVolume;

}
