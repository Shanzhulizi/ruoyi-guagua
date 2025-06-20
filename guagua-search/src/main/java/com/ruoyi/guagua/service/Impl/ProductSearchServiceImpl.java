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
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
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
     * @return
     * @throws IOException
     */
    public List<ESProductDTO> search(String keyword) throws IOException {
        // 构造多字段匹配查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (StringUtils.hasText(keyword)) {
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(
                    keyword, "name", "brand", "describe", "category"
            );
            boolQuery.must(multiMatchQuery);
        }

        // 构造查询请求
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(boolQuery)
                .size(100); // 你可以改为分页参数

        SearchRequest request = new SearchRequest("products"); // 索引名
        request.source(sourceBuilder);

        // 执行搜索
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        // 解析结果
        List<ESProductDTO> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (SearchHit hit : response.getHits().getHits()) {
            ESProductDTO dto = mapper.readValue(hit.getSourceAsString(), ESProductDTO.class);
            result.add(dto);
        }

        return result;
    }
}
