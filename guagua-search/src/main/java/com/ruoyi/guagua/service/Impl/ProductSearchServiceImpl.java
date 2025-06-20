package com.ruoyi.guagua.service.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.guagua.dto.ESProductDTO;
import com.ruoyi.guagua.dto.ProductSearchParamDTO;
import com.ruoyi.guagua.mapper.CategoryMapper;
import com.ruoyi.guagua.mapper.ProductMapper;
import com.ruoyi.guagua.service.ProductSearchService;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private static final String INDEX_NAME = "products";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ProductMapper productMapper; // 查询数据库

    @Autowired
    private CategoryMapper categoryMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void importAllProductsToES() throws IOException {
        List<ESProductDTO> products = productMapper.selectAllWithCategory();

        BulkRequest bulkRequest = new BulkRequest();

        for (ESProductDTO dto : products) {
            String json = objectMapper.writeValueAsString(dto);

            IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                    .id(String.valueOf(dto.getId()))
                    .source(json, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        if (bulkResponse.hasFailures()) {
            System.err.println("部分导入失败: " + bulkResponse.buildFailureMessage());
        } else {
            System.out.println("全部商品成功导入，共 " + products.size() + " 条");
        }
    }



    /**
     * 上传单个文档到ES
     * @param product
     */
    public void uploadProduct(ESProductDTO product) {
        try {
            // 转换为 JSON
            String json = objectMapper.writeValueAsString(product);

            IndexRequest request = new IndexRequest(INDEX_NAME)
                    .id(String.valueOf(product.getId()))
                    .source(json, XContentType.JSON);

            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

            if (response.getResult() == DocWriteResponse.Result.CREATED ||
                    response.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("上传成功：" + response.getId());
            } else {
                System.out.println("上传失败：" + response.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索功能
     * @param param
     * @return
     * @throws IOException
     */
    public List<ESProductDTO> search(ProductSearchParamDTO param) throws IOException {
//        构造一个“布尔查询”，可以组合多个 must（匹配）和 filter（筛选）条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 全文搜索字段
        if (StringUtils.hasText(param.getName())) {
            boolQuery.must(QueryBuilders.matchQuery("name", param.getName()));
        }

        if (StringUtils.hasText(param.getDescribe())) {
            boolQuery.must(QueryBuilders.matchQuery("describe", param.getDescribe()));
        }

        // 精确匹配字段
        if (StringUtils.hasText(param.getBrand())) {
            boolQuery.filter(QueryBuilders.termQuery("brand.keyword", param.getBrand()));
        }

        if (StringUtils.hasText(param.getCategory())) {
            boolQuery.filter(QueryBuilders.termQuery("category.keyword", param.getCategory()));
        }

        // 区间查询
        if (param.getMinPrice() != null || param.getMaxPrice() != null) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
            if (param.getMinPrice() != null) {
                rangeQuery.gte(param.getMinPrice());
            }
            if (param.getMaxPrice() != null) {
                rangeQuery.lte(param.getMaxPrice());
            }
            boolQuery.filter(rangeQuery);
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(boolQuery)
                .size(100); // 默认最多查100条

        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.source(sourceBuilder);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

//        创建一个返回列表 + JSON 转换工具 Jackson 的 ObjectMapper
        List<ESProductDTO> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (SearchHit hit : response.getHits().getHits()) {
            String json = hit.getSourceAsString();
            ESProductDTO dto = objectMapper.readValue(json, ESProductDTO.class);
            result.add(dto);
        }

        return result;
    }
}
