package com.ruoyi.guagua.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.vo.SeckillProductDisplayVO;
import com.ruoyi.guagua.vo.SeckillProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 秒杀商品（独立库存、独立活动）Mapper接口
 * 
 * @author lm
 * @date 2025-06-10
 */
@Mapper
public interface SeckillProductMapper 
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
     * 删除秒杀商品（独立库存、独立活动）
     * 
     * @param id 秒杀商品（独立库存、独立活动）主键
     * @return 结果
     */
    public int deleteSeckillProductById(Long id);

    /**
     * 批量删除秒杀商品（独立库存、独立活动）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeckillProductByIds(Long[] ids);

    /**
     * 获取首页推荐秒杀商品
     *
     */
    List<SeckillProduct> selectHotSeckillProducts();

    /**
     * 获取所有秒杀商品
     * @return
     */
    List<SeckillProductDisplayVO> selectAllSeckillProductList();

    /**
     * 获取某个秒杀商品详细信息
     * @param id
     * @return
     */
    SeckillProductVO selectSeckillProductDetailById(Long id);


    /**
     * 减库存逻辑
     * @param id
     * @return
     */
    @Update("UPDATE seckill_product SET available_stock = available_stock - 1 WHERE id = #{id} AND available_stock > 0")
    int reduceStock(Long id);

    /**
     * 缓存查不到，查数据库
     * @param productId
     * @return
     */
    Integer selectStockByProductId(Long productId);

    /**
     * 获取所有秒杀商品id
     * @return
     */
    List<Long> selectSeckillProductId();


    /**
     * 下面三个方法均与库存到Redis有关
     * ************************************************************
     * @param id
     * @return
     */
//    Integer selectStockById(Long id);
//
//    int updateStockById(Long productId, Integer stock);
//
//    List<SeckillProduct> selectProductsByTimeRange(LocalDateTime now, LocalDateTime endTime);
}
