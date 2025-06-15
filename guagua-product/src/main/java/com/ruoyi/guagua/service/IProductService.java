package com.ruoyi.guagua.service;

import java.util.List;

import com.ruoyi.guagua.domain.Product;
import com.ruoyi.guagua.vo.CategoryProductVO;

/**
 * 商品Service接口
 * 
 * @author lm
 * @date 2025-06-07
 */
public interface IProductService 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Product selectProductById(Long id);

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteProductById(Long id);


    /**
     * 获取推荐列表
     * @return
     */
    List<Product> recommendList();

    /**
     * 根据分类id获取商品列表
     *
     * @param categoryId
     * @return
     */
    List<CategoryProductVO> getByCategoryId(Long categoryId);
}
