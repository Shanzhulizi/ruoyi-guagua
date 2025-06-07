package com.ruoyi.guagua.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 product
 * 
 * @author lm
 * @date 2025-06-07
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 所属分类ID */
    @Excel(name = "所属分类ID")
    private Long categoryId;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 商品主图URL */
    @Excel(name = "商品主图URL")
    private String image;

    /** 商品轮播图（逗号分隔） */
    @Excel(name = "商品轮播图", readConverterExp = "逗=号分隔")
    private String images;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String description;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long stock;

    /** 状态（1=上架，0=下架） */
    @Excel(name = "状态", readConverterExp = "1==上架，0=下架")
    private Integer status;

    /** 是否推荐（1=是，0=否） */
    @Excel(name = "是否推荐", readConverterExp = "1==是，0=否")
    private Integer isRecommended;

    /** 是否新品 */
    @Excel(name = "是否新品")
    private Integer isNew;

    /** 是否热销 */
    @Excel(name = "是否热销")
    private Integer isHot;

    /** 销量 */
    @Excel(name = "销量")
    private Long salesVolume;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }

    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setIsRecommended(Integer isRecommended) 
    {
        this.isRecommended = isRecommended;
    }

    public Integer getIsRecommended() 
    {
        return isRecommended;
    }

    public void setIsNew(Integer isNew) 
    {
        this.isNew = isNew;
    }

    public Integer getIsNew() 
    {
        return isNew;
    }

    public void setIsHot(Integer isHot) 
    {
        this.isHot = isHot;
    }

    public Integer getIsHot() 
    {
        return isHot;
    }

    public void setSalesVolume(Long salesVolume) 
    {
        this.salesVolume = salesVolume;
    }

    public Long getSalesVolume() 
    {
        return salesVolume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("categoryId", getCategoryId())
            .append("brand", getBrand())
            .append("image", getImage())
            .append("images", getImages())
            .append("description", getDescription())
            .append("price", getPrice())
            .append("stock", getStock())
            .append("status", getStatus())
            .append("isRecommended", getIsRecommended())
            .append("isNew", getIsNew())
            .append("isHot", getIsHot())
            .append("salesVolume", getSalesVolume())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
