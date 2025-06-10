package com.ruoyi.guagua.service;

import java.util.List;
import com.ruoyi.guagua.domain.SeckillProduct;

/**
 * 秒杀商品（独立库存、独立活动）Service接口
 * 
 * @author lm
 * @date 2025-06-10
 */
public interface ISeckillProductService 
{
    /**
     * 查询秒杀商品（独立库存、独立活动）
     * 
     * @param id 秒杀商品（独立库存、独立活动）主键
     * @return 秒杀商品（独立库存、独立活动）
     */
    public SeckillProduct selectSeckillProductById(Long id);

    /**
     * 查询秒杀商品（独立库存、独立活动）列表
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 秒杀商品（独立库存、独立活动）集合
     */
    public List<SeckillProduct> selectSeckillProductList(SeckillProduct seckillProduct);

    /**
     * 新增秒杀商品（独立库存、独立活动）
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 结果
     */
    public int insertSeckillProduct(SeckillProduct seckillProduct);

    /**
     * 修改秒杀商品（独立库存、独立活动）
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 结果
     */
    public int updateSeckillProduct(SeckillProduct seckillProduct);

    /**
     * 批量删除秒杀商品（独立库存、独立活动）
     * 
     * @param ids 需要删除的秒杀商品（独立库存、独立活动）主键集合
     * @return 结果
     */
    public int deleteSeckillProductByIds(Long[] ids);

    /**
     * 删除秒杀商品（独立库存、独立活动）信息
     * 
     * @param id 秒杀商品（独立库存、独立活动）主键
     * @return 结果
     */
    public int deleteSeckillProductById(Long id);
}
