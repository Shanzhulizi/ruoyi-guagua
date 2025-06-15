package com.ruoyi.guagua.service;

import com.ruoyi.guagua.domain.CartItem;

import java.util.List;

public interface CartItemService {
    void addToCart(Long userId, Long productId, Integer quantity);

    List<CartItem> getCartList(Long userId);

    void updateQuantity(Long userId, Long productId, Integer quantity);

    void removeItem(Long userId, Long productId);
}
