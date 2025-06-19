package com.ruoyi.guagua.controller;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.guagua.dto.ESProductDTO;
import com.ruoyi.guagua.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es/product")
public class ElasticSearchController {


    @Autowired
    private ProductSearchService productSearchService;

    /**
     * //TODO 日后如果还有机会再优化
     * 本来这里应该是后台的接口进行批量导入，但是我懒得弄了，直接用apipost全部导入
     * @return
     */
    @PostMapping("/importAll")
    public AjaxResult importAll() {
        try {
            productSearchService.importAllProductsToES();
            return AjaxResult.success("商品全部导入 Elasticsearch 成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("导入失败: " + e.getMessage());
        }
    }


    // 上传商品数据到 ES
    @PostMapping("/upload")
    public AjaxResult uploadProduct(@RequestBody ESProductDTO product) {
        try {
            productSearchService.uploadProduct(product);
            return AjaxResult.success("商品已上传到ES");
        } catch (Exception e) {
            return AjaxResult.error("上传失败：" + e.getMessage());

        }
    }



    // 搜索商品
//    @GetMapping("/query")
//    public R<List<ESProductDTO>> search(@RequestParam String keyword) {
//        return R.ok(productSearchService.search(keyword));
//    }
}
