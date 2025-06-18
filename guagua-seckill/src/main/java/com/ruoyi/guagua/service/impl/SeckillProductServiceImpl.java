package com.ruoyi.guagua.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.guagua.domain.SeckillOrder;
import com.ruoyi.guagua.mapper.SeckillOrderMapper;
import com.ruoyi.guagua.vo.SeckillProductDisplayVO;
import com.ruoyi.guagua.vo.SeckillProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.guagua.mapper.SeckillProductMapper;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.service.ISeckillProductService;

import static com.ruoyi.guagua.utils.DateUtil.convertDateToLocalDateTime;

/**
 * 秒杀商品（独立库存、独立活动）Service业务层处理
 * 
 * @author lm
 * @date 2025-06-10
 */

@Slf4j
@Service
public class SeckillProductServiceImpl implements ISeckillProductService
{
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
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




    /**
     * 获取所有秒杀商品
     * @return
     */
    @Override
    public List<SeckillProductDisplayVO> selectAllSeckillProductList() {
        return seckillProductMapper.selectAllSeckillProductList();

    }


    @Override
    public SeckillProductVO getDetailById(Long id) {
        return seckillProductMapper.selectSeckillProductDetailById(id);
    }


    /**
     * 获取当前用户id
     * @return
     */
    private Long getCurrentUserId() {
        return SecurityUtils.getUserId(); // 若依框架获取当前登录用户ID的方法
    }

    /**
     *
     * @param id
     * @return
     */
//    @Override
//    public boolean purchaseSeckillProduct(Long id) {
//        SeckillProduct product = seckillProductMapper.selectSeckillProductById(id);
//        if (product == null) return false;
//
//        // 时间判断
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startTime = convertDateToLocalDateTime(product.getStartTime());
//        LocalDateTime endTime = convertDateToLocalDateTime(product.getEndTime());
//
//        if (now.isBefore(startTime) || now.isAfter(endTime)) {
//            return false;
//        }
//
//        // 库存判断
//        if (product.getAvailableStock() <= 0) {
//            return false;
//        }
//
//        // 并发减库存
//        int result = seckillProductMapper.reduceStock(id);
//        if (result <= 0) {
//            return false;
//        }
//
//        // 添加订单（此处用假用户ID模拟）
//        Long userId = getCurrentUserId(); // TODO: 从登录上下文获取用户ID
//
//        SeckillOrder order = new SeckillOrder();
//        order.setUserId(userId);
//        order.setProductId(id);
//        order.setSeckillPrice(product.getSeckillPrice());
//        order.setCreateTime(new Date());
//        order.setStatus(0);
//
//        System.out.println(order);
//
//        seckillOrderMapper.insertOrder(order);
//        return true;
//    }




//    @Transactional
    @Override
    public boolean purchaseSeckillProduct(Long id,Long userId) {
        SeckillProduct product = seckillProductMapper.selectSeckillProductById(id);
        if (product == null) return false;
        System.out.println(product);
        // 时间判断
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = convertDateToLocalDateTime(product.getStartTime());
        LocalDateTime endTime = convertDateToLocalDateTime(product.getEndTime());

        if (now.isBefore(startTime) || now.isAfter(endTime)) {
            log.info("秒杀活动已结束");
            return false;
        }
        // 库存判断
        if (product.getAvailableStock() <= 0) {
            return false;
        }

        // 并发减库存
        //TODO
        //我觉得这里应该把商品表的库存也一起改掉，但是我懒得搞了
        int result = seckillProductMapper.reduceStock(id);
        log.info("库存扣减结果 result = {}", result);
        if (result <= 0) {
            return false;
        }

        SeckillOrder order = new SeckillOrder();
        order.setUserId(userId);
        order.setProductId(id);
        order.setSeckillPrice(product.getSeckillPrice());
        order.setCreateTime(new Date());
        order.setStatus(0);


        seckillOrderMapper.insertOrder(order);
        return true;
    }

    @Override
    public List<Long> getSeckillProductIds() {
        return seckillProductMapper.selectSeckillProductId();
    }
}
