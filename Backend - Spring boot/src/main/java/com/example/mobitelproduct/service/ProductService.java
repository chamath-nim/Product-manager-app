package com.example.mobitelproduct.service;

import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.dto.ProductHisDto;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;

public interface ProductService {

    ResponseHandler<ProductDto> mainSearch(RequestHandler<ProductDto> ptRequest);

    ResponseHandler<ProductHisDto> findAllHistoryPages(RequestHandler<ProductHisDto> ptRequest);

    ResponseHandler<ProductDto> addProduct(RequestHandler<ProductDto> ptRequest);

    ResponseHandler<ProductDto> updateProduct(RequestHandler<ProductDto> ptRequest);

    ResponseHandler<ProductDto> deleteProductById(Long id);

}
