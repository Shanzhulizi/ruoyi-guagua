package com.ruoyi.guagua.controller;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.guagua.domain.Product;
import com.ruoyi.guagua.dto.ESProductDTO;
import com.ruoyi.guagua.dto.ProductSearchParamDTO;
import com.ruoyi.guagua.service.IProductService;
import com.ruoyi.guagua.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/es/product")
public class ElasticSearchController {


    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private IProductService productService;
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
    @PostMapping("/search")
    public AjaxResult search
            (@RequestBody Map<String, String> param) {

            try {
                String keyword = param.get("keyword");
            log.info(String.valueOf(keyword));
            List<ESProductDTO> list = productSearchService.search(keyword);
            return AjaxResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("搜索失败: " + e.getMessage());
        }
    }


    @PostMapping("/batchGetByIds")
    public AjaxResult batchGetByIds(@RequestBody List<Long> ids) {
        List<Product> products = productService.getByIds(ids);
        return AjaxResult.success(products);
    }
}
