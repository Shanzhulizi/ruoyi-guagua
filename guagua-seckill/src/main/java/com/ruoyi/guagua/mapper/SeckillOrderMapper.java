package com.ruoyi.guagua.mapper;

import com.ruoyi.guagua.domain.SeckillOrder;
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.vo.SeckillProductDisplayVO;
import com.ruoyi.guagua.vo.SeckillProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 秒杀订单Mapper接口
 * 
 * @author lm
 * @date 2025-06-11
 */
@Mapper
public interface SeckillOrderMapper
{
    int insertOrder(SeckillOrder order);
}
