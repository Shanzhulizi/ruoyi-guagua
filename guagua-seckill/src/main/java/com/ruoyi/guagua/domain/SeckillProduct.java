package com.ruoyi.guagua.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 秒杀商品（独立库存、独立活动）对象 seckill_product
 * 
 * @author lm
 * @date 2025-06-10
 */
public class SeckillProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 秒杀商品主键ID */
    private Long id;

    /** 秒杀活动ID（支持多活动） */
    @Excel(name = "秒杀活动ID", readConverterExp = "支=持多活动")
    private Long activityId;

    /** 商品ID（关联商品表） */
    @Excel(name = "商品ID", readConverterExp = "关=联商品表")
    private Long productId;

    /** 原价 */
    @Excel(name = "原价")
    private BigDecimal originalPrice;

    /** 秒杀价 */
    @Excel(name = "秒杀价")
    private BigDecimal seckillPrice;

    /** 秒杀库存总量 */
    @Excel(name = "秒杀库存总量")
    private String totalStock;

    /** 当前剩余库存（预减库存用） */
    @Excel(name = "当前剩余库存", readConverterExp = "预=减库存用")
    private String availableStock;

    /** 已售数量（方便统计） */
    @Excel(name = "已售数量", readConverterExp = "方=便统计")
    private String soldCount;

    /** 每个用户限购数量 */
    @Excel(name = "每个用户限购数量")
    private String limitPerUser;

    /** 秒杀开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "秒杀开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 秒杀结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "秒杀结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 是否热门秒杀（用于首页推荐，0=否，1=是） */
    @Excel(name = "是否热门秒杀", readConverterExp = "用=于首页推荐，0=否，1=是")
    private Long isHot;

    /** 状态（0=未启用，1=启用，2=结束） */
    @Excel(name = "状态", readConverterExp = "0==未启用，1=启用，2=结束")
    private Long status;

    /** 逻辑删除标志（1=删除） */
    @Excel(name = "逻辑删除标志", readConverterExp = "1==删除")
    private Long isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    public void setOriginalPrice(BigDecimal originalPrice) 
    {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getOriginalPrice() 
    {
        return originalPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) 
    {
        this.seckillPrice = seckillPrice;
    }

    public BigDecimal getSeckillPrice() 
    {
        return seckillPrice;
    }

    public void setTotalStock(String totalStock) 
    {
        this.totalStock = totalStock;
    }

    public String getTotalStock() 
    {
        return totalStock;
    }

    public void setAvailableStock(String availableStock) 
    {
        this.availableStock = availableStock;
    }

    public String getAvailableStock() 
    {
        return availableStock;
    }

    public void setSoldCount(String soldCount) 
    {
        this.soldCount = soldCount;
    }

    public String getSoldCount() 
    {
        return soldCount;
    }

    public void setLimitPerUser(String limitPerUser) 
    {
        this.limitPerUser = limitPerUser;
    }

    public String getLimitPerUser() 
    {
        return limitPerUser;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setIsHot(Long isHot) 
    {
        this.isHot = isHot;
    }

    public Long getIsHot() 
    {
        return isHot;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("productId", getProductId())
            .append("originalPrice", getOriginalPrice())
            .append("seckillPrice", getSeckillPrice())
            .append("totalStock", getTotalStock())
            .append("availableStock", getAvailableStock())
            .append("soldCount", getSoldCount())
            .append("limitPerUser", getLimitPerUser())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("isHot", getIsHot())
            .append("status", getStatus())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
