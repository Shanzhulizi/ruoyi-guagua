package com.ruoyi.guagua.service;

import java.util.List;
import com.ruoyi.guagua.domain.Category;
import com.ruoyi.guagua.vo.CategoryVO;

/**
 * 商品种类Service接口
 * 
 * @author lm
 * @date 2025-06-07
 */
public interface ICategoryService 
{
    /**
     * 查询商品种类
     * 
     * @param id 商品种类主键
     * @return 商品种类
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询商品种类列表
     * 
     * @param category 商品种类
     * @return 商品种类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品种类
     * 
     * @param category 商品种类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品种类
     * 
     * @param category 商品种类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品种类
     * 
     * @param ids 需要删除的商品种类主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    /**
     * 删除商品种类信息
     * 
     * @param id 商品种类主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    List<CategoryVO> selectCategoryList();
}
