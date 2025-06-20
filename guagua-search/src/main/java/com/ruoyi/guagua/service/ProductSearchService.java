package com.ruoyi.guagua.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.guagua.dto.ESProductDTO;
import com.ruoyi.guagua.dto.ProductSearchParamDTO;

import java.io.IOException;
import java.util.List;

public interface ProductSearchService {
    void uploadProduct(ESProductDTO product)            ;

    void importAllProductsToES() throws IOException;

    List<ESProductDTO> search(ProductSearchParamDTO param) throws IOException;
}
