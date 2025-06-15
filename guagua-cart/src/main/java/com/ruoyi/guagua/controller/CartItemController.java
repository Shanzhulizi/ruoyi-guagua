package com.ruoyi.guagua.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody CartItem item) {
        cartItemService.addToCart(item.getUserId(), item.getProductId(), item.getQuantity());
        return AjaxResult.success("添加成功");
    }

    @GetMapping("/list")
    public AjaxResult list(@RequestParam Long userId) {
        List<CartItem> list = cartItemService.getCartList(userId);
        return AjaxResult.success(list);
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody CartItem item) {
        cartItemService.updateQuantity(item.getUserId(), item.getProductId(), item.getQuantity());
        return AjaxResult.success();
    }

    @DeleteMapping("/remove")
    public AjaxResult remove(@RequestParam Long userId, @RequestParam Long productId) {
        cartItemService.removeItem(userId, productId);
        return AjaxResult.success();
    }

}
