package com.ruoyi.guagua.service;

import com.ruoyi.guagua.dto.ESProductDTO;

import java.io.IOException;
import java.util.List;

public interface ProductSearchService {
    void uploadProduct(ESProductDTO product)            ;

    void importAllProductsToES() throws IOException;

    List<ESProductDTO> search(String keyword) throws IOException;
}
