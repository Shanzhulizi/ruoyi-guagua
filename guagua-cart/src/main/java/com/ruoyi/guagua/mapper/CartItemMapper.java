package com.ruoyi.guagua.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.guagua.domain.CartItem;
import com.ruoyi.guagua.vo.CartItemShowVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartItemMapper {
    CartItem selectByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    int insertCartItem(CartItem item);

    int updateCartItemQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

    int updateCartItem(CartItem item);

    int markCartItemDeleted(@Param("userId") Long userId, @Param("productId") Long productId);

    List<CartItemShowVO> selectCartListByUserId(@Param("userId") Long userId);

    int getCount(Long userId);
}