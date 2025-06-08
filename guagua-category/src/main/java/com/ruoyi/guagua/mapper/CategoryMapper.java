package com.ruoyi.guagua.mapper;

import java.util.List;
import com.ruoyi.guagua.domain.Category;
import com.ruoyi.guagua.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品种类Mapper接口
 * 
 * @author lm
 * @date 2025-06-07
 */
@Mapper
public interface CategoryMapper 
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
     * 删除商品种类
     * 
     * @param id 商品种类主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除商品种类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);


    List<CategoryVO> selectCategories();
}
