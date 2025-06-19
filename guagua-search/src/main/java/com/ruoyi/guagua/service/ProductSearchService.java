package com.ruoyi.guagua.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.guagua.dto.ESProductDTO;

import java.io.IOException;

public interface ProductSearchService {
    void uploadProduct(ESProductDTO product)            ;

    void importAllProductsToES() throws IOException;
}
