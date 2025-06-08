package com.ruoyi.guagua.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.guagua.vo.CategoryVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.guagua.domain.Category;
import com.ruoyi.guagua.service.ICategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品种类Controller
 * 
 * @author lm
 * @date 2025-06-07
 */
@RestController
@RequestMapping("/category/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getList")
    public AjaxResult getList()
    {
        List<CategoryVO> list = categoryService.selectCategoryList();
        return AjaxResult.success(list);
    }


    /**
     * ************************************************************
     * 若依自带
     */

    /**
     * 查询商品种类列表
     */
    @PreAuthorize("@ss.hasPermi('category:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品种类列表
     */
    @PreAuthorize("@ss.hasPermi('category:category:export')")
    @Log(title = "商品种类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品种类数据");
    }

    /**
     * 获取商品种类详细信息
     */
    @PreAuthorize("@ss.hasPermi('category:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryService.selectCategoryById(id));
    }

    /**
     * 新增商品种类
     */
    @PreAuthorize("@ss.hasPermi('category:category:add')")
    @Log(title = "商品种类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品种类
     */
    @PreAuthorize("@ss.hasPermi('category:category:edit')")
    @Log(title = "商品种类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品种类
     */
    @PreAuthorize("@ss.hasPermi('category:category:remove')")
    @Log(title = "商品种类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
