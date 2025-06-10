package com.ruoyi.guagua.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.guagua.domain.SeckillProduct;
import com.ruoyi.guagua.service.ISeckillProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 秒杀商品（独立库存、独立活动）Controller
 * 
 * @author lm
 * @date 2025-06-10
 */
@RestController
@RequestMapping("/seckill/seckill")
public class SeckillProductController extends BaseController
{
    @Autowired
    private ISeckillProductService seckillProductService;


    @GetMapping("/hot")
    public List<SeckillProduct> getHotSeckillProducts(){
        return seckillProductService.getHotSeckillProducts();
    }


    /**
     * *******************************************
     * 以下为若依自带
     */



    /**
     * 查询秒杀商品（独立库存、独立活动）列表
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeckillProduct seckillProduct)
    {
        startPage();
        List<SeckillProduct> list = seckillProductService.selectSeckillProductList(seckillProduct);
        return getDataTable(list);
    }

    /**
     * 导出秒杀商品（独立库存、独立活动）列表
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:export')")
    @Log(title = "秒杀商品（独立库存、独立活动）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillProduct seckillProduct)
    {
        List<SeckillProduct> list = seckillProductService.selectSeckillProductList(seckillProduct);
        ExcelUtil<SeckillProduct> util = new ExcelUtil<SeckillProduct>(SeckillProduct.class);
        util.exportExcel(response, list, "秒杀商品（独立库存、独立活动）数据");
    }

    /**
     * 获取秒杀商品（独立库存、独立活动）详细信息
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(seckillProductService.selectSeckillProductById(id));
    }

    /**
     * 新增秒杀商品（独立库存、独立活动）
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:add')")
    @Log(title = "秒杀商品（独立库存、独立活动）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillProduct seckillProduct)
    {
        return toAjax(seckillProductService.insertSeckillProduct(seckillProduct));
    }

    /**
     * 修改秒杀商品（独立库存、独立活动）
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:edit')")
    @Log(title = "秒杀商品（独立库存、独立活动）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillProduct seckillProduct)
    {
        return toAjax(seckillProductService.updateSeckillProduct(seckillProduct));
    }

    /**
     * 删除秒杀商品（独立库存、独立活动）
     */
    @PreAuthorize("@ss.hasPermi('seckill:seckill:remove')")
    @Log(title = "秒杀商品（独立库存、独立活动）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillProductService.deleteSeckillProductByIds(ids));
    }
}
