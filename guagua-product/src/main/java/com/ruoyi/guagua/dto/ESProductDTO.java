package com.ruoyi.guagua.dto;


import lombok.Data;

@Data
public class ESProductDTO {

    private Long   id;
    private String name;
    private String brand;
//    private String categoryName;
    private String category;
    private Double price;
    private String describe;
}