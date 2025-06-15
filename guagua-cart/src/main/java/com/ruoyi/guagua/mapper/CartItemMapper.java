package com.ruoyi.guagua.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.guagua.domain.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    //继承BaseMapper后不需要重写
//    CartItem selectOne(QueryWrapper<CartItem> eq);
//
//    void updateById(CartItem existing);
//
//    void insert(CartItem item);
//
//    List<CartItem> selectList(QueryWrapper<CartItem> eq);
//
//    void update(Object o, UpdateWrapper<CartItem> eq);
}
