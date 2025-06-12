package com.ruoyi.guagua.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.guagua.domain.SeckillMessage;
import com.ruoyi.guagua.mq.SeckillProducer;
import com.ruoyi.guagua.redis.RedisSeckillService;
import com.ruoyi.guagua.service.SeckillOrderService;
import com.ruoyi.guagua.vo.SeckillProductDisplayVO;
import com.ruoyi.guagua.vo.SeckillProductVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private SeckillProducer seckillProducer;

    @Autowired
    RedisSeckillService redisSeckillService;
    @GetMapping("/hot")
    public List<SeckillProduct> getHotSeckillProducts(){
        return seckillProductService.getHotSeckillProducts();
    }

    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
//        startPage();
        List<SeckillProductDisplayVO> list = seckillProductService.selectAllSeckillProductList();
        System.out.println("秒杀商品数量 ：" + list.size());
        return AjaxResult.success(list);
    }


    @GetMapping("/detail/{id}")
    public AjaxResult getDetail(@PathVariable("id") Long id) {
        SeckillProductVO vo = seckillProductService.getDetailById(id);
        if (vo != null) {
            return AjaxResult.success(vo);
        } else {
            return AjaxResult.error("商品不存在");
        }
    }


    /**
     * 这是最初的逻辑
     * @param id
     * @param userId
     * @return
     */
//    @PostMapping("/purchase/{id}")
//    public AjaxResult purchase(@PathVariable Long id) {
//        boolean success = seckillProductService.purchaseSeckillProduct(id);
//        if (success) {
//            return AjaxResult.success("购买成功！");
//        } else {
//            return AjaxResult.error("库存不足或活动未开始");
//        }
//    }


    /**
     * 这是为了让jmeter方便测试的改造版，最终还是要改回去的
     * @param id
     * @param userId
     * @return
     */
//    @PostMapping("/purchase/{id}")
//    public AjaxResult purchase(@PathVariable Long id , @RequestParam Long userId) {
//        boolean success = seckillProductService.purchaseSeckillProduct(id, userId);
//        if (success) {
//            return AjaxResult.success("购买成功！");
//        } else {
//            return AjaxResult.error("库存不足或活动未开始");
//        }
//    }


    /**
     * 这是为了让jmeter方便测试的改造版，添加了异步mq功能
     * @param id
     * @param userId
     * @return
     */
//    @PostMapping("/purchase/{id}")
//    public AjaxResult purchase(@PathVariable Long id , @RequestParam Long userId) {
//        // 构造秒杀消息
//        SeckillMessage message = SeckillMessage.builder()
//                .userId(userId)
//                .productId(id)
//                .build();
//
//        // 发消息到 MQ
//        seckillProducer.sendSeckillMessage(message);
//
//        return AjaxResult.success("请求已排队，请稍候查看订单状态");
//    }

    /**
     * 在mq的基础上，添加Redis
     * @param productId
     * @param userId
     * @return
     */
    @PostMapping("/purchase/{productId}")
    public AjaxResult purchase(@PathVariable Long productId, @RequestParam Long userId) {
        long result = redisSeckillService.executeSeckill(productId, userId);
        if (result == 0) return AjaxResult.error("库存不足");
        if (result == 2) return AjaxResult.error("请勿重复抢购");

        SeckillMessage message = SeckillMessage.builder()
                .userId(userId).productId(productId).build();
        seckillProducer.sendSeckillMessage(message);

        return AjaxResult.success("抢购成功，正在为您创建订单");
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
        List<SeckillProductDisplayVO> list = seckillProductService.selectAllSeckillProductList();
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
