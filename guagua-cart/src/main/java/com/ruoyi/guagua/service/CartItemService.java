package com.ruoyi.guagua.service;

import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.vo.CartItemShowVO;

import java.util.List;

public interface CartItemService {
    void addToCart(Long userId, Long productId, Integer quantity);
    void updateCartItem(Long userId, Long productId, Integer quantity);
    void deleteCartItem(Long userId, Long productId);
    List<CartItemShowVO> getCartList(Long userId);

    int getCount(Long userId);
}
