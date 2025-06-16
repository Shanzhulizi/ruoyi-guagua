package com.ruoyi.guagua.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.domain.CartItemDTO;
import com.ruoyi.guagua.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    //获取购物车商品数
    @GetMapping("/count")
    public AjaxResult getCount( @RequestParam Long userId          ){
       int count= cartItemService.getCount(userId);

        return AjaxResult.success(count);
    }


    // 添加商品到购物车（如果已存在则数量增加）
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CartItemDTO item) {

        cartItemService.addToCart(item.getUserId(), item.getProductId(), item.getQuantity());

        return AjaxResult.success("添加成功");
    }

    // 修改购物车某项的数量
    @PutMapping("/update")
    public AjaxResult update(@RequestBody CartItemDTO item) {
        cartItemService.updateCartItem(item.getUserId(), item.getProductId(), item.getQuantity());
        return AjaxResult.success("修改成功");
    }

    // 删除购物车某个商品
    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestBody CartItemDTO item) {
        cartItemService.deleteCartItem(item.getUserId(), item.getProductId());
        return AjaxResult.success("删除成功");
    }

    // 查询用户购物车列表
    @GetMapping("/list/{userId}")
    public AjaxResult list(@PathVariable Long userId) {
        log.info("userId", userId);
        return AjaxResult.success(cartItemService.getCartList(userId));
    }
}
