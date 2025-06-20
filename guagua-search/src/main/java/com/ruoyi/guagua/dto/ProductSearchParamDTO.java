package com.ruoyi.guagua.dto;


import lombok.Data;

//搜索参数类
@Data
public class ProductSearchParamDTO {
    private String name;
    private String brand;
    private String category;
//private String categoryName; //  改为 categoryName
    private Double minPrice;
    private Double maxPrice;
    private String describe;
}