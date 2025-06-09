package com.ruoyi.guagua.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecommendProductVO {


        /** 商品ID */
        private Long id;

        /** 商品名称 */
        private String name;

//        /** 所属分类ID */
//        @Excel(name = "所属分类ID")
//        private Long categoryId;

        /** 品牌 */
        private String brand;

        /** 商品主图URL */
        private String image;



        private String description;

        /** 价格 */
        private BigDecimal price;

//        /** 库存数量 */
//        @Excel(name = "库存数量")
//        private Long stock;


        /** 销量 */
        private Long salesVolume;



}
