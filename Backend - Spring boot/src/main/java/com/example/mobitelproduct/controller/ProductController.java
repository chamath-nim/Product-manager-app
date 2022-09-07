package com.example.mobitelproduct.controller;

import com.example.mobitelproduct.commonUrl;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.dto.ProductHisDto;
import com.example.mobitelproduct.service.ProductService;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(commonUrl.getALLWithFilters)
    public ResponseHandler<ProductDto> mainSearch(@RequestBody RequestHandler<ProductDto> ptRequest) {
        return productService.mainSearch(ptRequest);
    }

    @PostMapping(commonUrl.findAllHisPage)
    public ResponseHandler<ProductHisDto> findAllHistoryPages(@RequestBody RequestHandler<ProductHisDto> ptRequest) {
        return productService.findAllHistoryPages(ptRequest);
    }

    @PostMapping(commonUrl.create)
    public ResponseHandler<ProductDto> create(@RequestBody RequestHandler<ProductDto> ptRequest) {
        return productService.addProduct(ptRequest);
    }

    @PutMapping(commonUrl.update)
    public ResponseHandler<ProductDto> update(@RequestBody RequestHandler<ProductDto> ptRequest) {
        return productService.updateProduct(ptRequest);
    }

    @DeleteMapping(commonUrl.delete)
    public ResponseHandler<ProductDto> delete(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

}
