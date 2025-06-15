package com.ruoyi.guagua.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.mapper.CartItemMapper;
import com.ruoyi.guagua.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartItemItemServiceImpl implements CartItemService {

    @Autowired
    CartItemMapper cartItemMapper;

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        CartItem existing = cartItemMapper.selectOne(new QueryWrapper<CartItem>()
                .eq("user_id", userId)
                .eq("product_id", productId)
                .eq("del_flag", "0"));

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartItemMapper.updateById(existing);
        } else {
            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setDelFlag("0");
            item.setCreateTime(new Date());
            item.setUpdateTime(new Date());
            cartItemMapper.insert(item);
        }
    }

    @Override
    public List<CartItem> getCartList(Long userId) {
        return cartItemMapper.selectList(new QueryWrapper<CartItem>()
                .eq("user_id", userId)
                .eq("del_flag", "0"));
    }

    @Override
    public void updateQuantity(Long userId, Long productId, Integer quantity) {
        cartItemMapper.update(null, new UpdateWrapper<CartItem>()
                .set("quantity", quantity)
                .eq("user_id", userId)
                .eq("product_id", productId)
                .eq("del_flag", "0"));
    }

    @Override
    public void removeItem(Long userId, Long productId) {
        cartItemMapper.update(null, new UpdateWrapper<CartItem>()
                .set("del_flag", "1")
                .eq("user_id", userId)
                .eq("product_id", productId));
    }


}
