package com.ruoyi.guagua.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.guagua.domain.Product;
import com.ruoyi.guagua.dto.ESProductDTO;
import com.ruoyi.guagua.vo.CategoryProductVO;
import com.ruoyi.guagua.vo.RecommendProductVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper接口
 * 
 * @author lm
 * @date 2025-06-07
 */
@Mapper
public interface ProductMapper
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
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * 获取推荐列表
     * @return
     */
    List<Product> selectRecommendedProductList();

    /**
     * 根据分类id获取商品
     * @param categoryId
     * @return
     */
    List<CategoryProductVO> selectProductListByCategoryId(Long categoryId);


    /**
     *
     * @return
     */
    List<ESProductDTO> selectAllWithCategory();


    List<Product> selectByIds(List<Long> ids);
}
