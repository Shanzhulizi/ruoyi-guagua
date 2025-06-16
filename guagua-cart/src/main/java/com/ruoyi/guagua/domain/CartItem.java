package com.ruoyi.guagua.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
//MyBatis-Plus 提供的注解，用于在实体类中配置与数据库表的映射关系
@TableName("cart_item")
public class CartItem {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
}