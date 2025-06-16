package com.ruoyi.guagua.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.mapper.CartItemMapper;
import com.ruoyi.guagua.service.CartItemService;
import com.ruoyi.guagua.vo.CartItemShowVO;
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
        CartItem existing = cartItemMapper.selectByUserIdAndProductId(userId, productId);

        System.out.println("exting  "+existing);

        if (existing != null) {
            cartItemMapper.updateCartItemQuantity(existing.getId(), quantity);
        } else {
            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setDelFlag("0");
            item.setCreateTime(new Date());
            item.setUpdateTime(new Date());
            cartItemMapper.insertCartItem(item);
        }
    }

    @Override
    public void updateCartItem(Long userId, Long productId, Integer quantity) {
        CartItem existing = cartItemMapper.selectByUserIdAndProductId(userId, productId);
        if (existing != null) {
            existing.setQuantity(quantity);
            existing.setUpdateTime(new Date());
            cartItemMapper.updateCartItem(existing);
        }
    }

    @Override
    public void deleteCartItem(Long userId, Long productId) {
        cartItemMapper.markCartItemDeleted(userId, productId);
    }

    @Override
    public List<CartItemShowVO> getCartList(Long userId) {
        return cartItemMapper.selectCartListByUserId(userId);
    }

    @Override
    public int getCount(Long userId) {
        return cartItemMapper.getCount(userId);
    }

}
