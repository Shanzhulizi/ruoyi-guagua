package com.ruoyi.guagua.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.guagua.mapper.SeckillProductMapper;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.service.ISeckillProductService;

/**
 * 秒杀商品（独立库存、独立活动）Service业务层处理
 * 
 * @author lm
 * @date 2025-06-10
 */
@Service
public class SeckillProductServiceImpl implements ISeckillProductService 
{
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    /**
     * 查询秒杀商品（独立库存、独立活动）
     * 
     * @param id 秒杀商品（独立库存、独立活动）主键
     * @return 秒杀商品（独立库存、独立活动）
     */
    @Override
    public SeckillProduct selectSeckillProductById(Long id)
    {
        return seckillProductMapper.selectSeckillProductById(id);
    }

    /**
     * 查询秒杀商品（独立库存、独立活动）列表
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 秒杀商品（独立库存、独立活动）
     */
    @Override
    public List<SeckillProduct> selectSeckillProductList(SeckillProduct seckillProduct)
    {
        return seckillProductMapper.selectSeckillProductList(seckillProduct);
    }

    /**
     * 新增秒杀商品（独立库存、独立活动）
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 结果
     */
    @Override
    public int insertSeckillProduct(SeckillProduct seckillProduct)
    {
        seckillProduct.setCreateTime(DateUtils.getNowDate());
        return seckillProductMapper.insertSeckillProduct(seckillProduct);
    }

    /**
     * 修改秒杀商品（独立库存、独立活动）
     * 
     * @param seckillProduct 秒杀商品（独立库存、独立活动）
     * @return 结果
     */
    @Override
    public int updateSeckillProduct(SeckillProduct seckillProduct)
    {
        seckillProduct.setUpdateTime(DateUtils.getNowDate());
        return seckillProductMapper.updateSeckillProduct(seckillProduct);
    }

    /**
     * 批量删除秒杀商品（独立库存、独立活动）
     * 
     * @param ids 需要删除的秒杀商品（独立库存、独立活动）主键
     * @return 结果
     */
    @Override
    public int deleteSeckillProductByIds(Long[] ids)
    {
        return seckillProductMapper.deleteSeckillProductByIds(ids);
    }

    /**
     * 删除秒杀商品（独立库存、独立活动）信息
     * 
     * @param id 秒杀商品（独立库存、独立活动）主键
     * @return 结果
     */
    @Override
    public int deleteSeckillProductById(Long id)
    {
        return seckillProductMapper.deleteSeckillProductById(id);
    }

    /**
     * 获取首页推荐秒杀商品
     * @return
     */
    @Override
    public List<SeckillProduct> getHotSeckillProducts() {
        return seckillProductMapper.selectHotSeckillProducts();
    }
}
