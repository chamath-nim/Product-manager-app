package com.example.mobitelproduct.service;

import com.example.mobitelproduct.HeaderMapper;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.dto.ProductHisDto;
import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.entity.ProductHis;
import com.example.mobitelproduct.repository.ProductCriteriaRepo;
import com.example.mobitelproduct.repository.ProductHisRepo;
import com.example.mobitelproduct.repository.ProductRepo;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductHisRepo productHisRepo;
    @Autowired
    private ModelMapper modelMapper;

    private final ProductCriteriaRepo productCriteriaRepo;

    public ProductServiceImpl(ProductCriteriaRepo productCriteriaRepo) {
        this.productCriteriaRepo = productCriteriaRepo;
    }

    public ResponseHandler<ProductDto> mainSearch(RequestHandler<ProductDto> ptRequest){
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        if (Objects.nonNull(ptRequest.getBody())) {
            Product p = modelMapper.map(ptRequest.getBody(), Product.class);
            Page<Product> productPage = productCriteriaRepo.findAllWithFilters(ptRequest.getPageSize(),
                                                                            ptRequest.getPageNumber(), p);
            List<Product> list = productPage.getContent();
            Long count = productPage.getTotalElements();
            TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {
            };
            List<ProductDto> page = modelMapper.map(list, typeToken.getType());
            ptResponse.setParaList(page);
            ptResponse.setCount(count);
            }

        else{
            ptResponse = getAllToPages(Math.toIntExact(ptRequest.getPageSize()),
                    Math.toIntExact(ptRequest.getPageNumber()));
        };
        return ptResponse;
    }

    public ResponseHandler<ProductHisDto> findAllHistoryPages(RequestHandler<ProductHisDto> ptRequest) {
        System.out.println(ptRequest.getPageSize()+" "+ptRequest.getPageNumber());
        ResponseHandler<ProductHisDto> ptResponse = new ResponseHandler<>();

        Pageable paging = PageRequest.of(Math.toIntExact(ptRequest.getPageNumber()),
                                         Math.toIntExact(ptRequest.getPageSize()));

        Long count = productHisRepo.numOfRows();
        System.out.println(count);
        Page<ProductHis> productList = productHisRepo.findAll(paging);
        List<ProductHis> productList1 = productList.getContent();
        TypeToken<List<ProductHisDto>> typeToken = new TypeToken<>() {
        };
        List<ProductHisDto> list = modelMapper.map(productList1, typeToken.getType());
        ptResponse.setParaList(list);
        ptResponse.setCount(count);
        HeaderMapper.success(ptResponse, "find All By according to the pagination");
        return ptResponse;
    }
    public ResponseHandler<ProductDto> getAllToPages(int pageSize, int pageNumber) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Long count = productRepo.numOfRows();
        Page<Product> productList = productRepo.findAll(paging);
        List<Product> productList1 = productList.getContent();
        TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {
        };
        List<ProductDto> list = modelMapper.map(productList1, typeToken.getType());
        ptResponse.setParaList(list);
        ptResponse.setCount(count);
        HeaderMapper.success(ptResponse, "find All By according to the pagination");
        return ptResponse;
    }

    public ResponseHandler<ProductDto> addProduct(RequestHandler<ProductDto> ptRequest) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setCreatedBy(ptRequest.requestHeader.getCreatedBy());
        p.setCrDateTime(CurrentDateTime());
        Product product = productRepo.save(p);
        ptResponse.setBody(modelMapper.map(product, ProductDto.class));
        HeaderMapper.success(ptResponse, "created");
        return ptResponse;
    }

    public ResponseHandler<ProductDto> updateProduct(RequestHandler<ProductDto> ptRequest) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();
        String currentDateTime = CurrentDateTime();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setCreatedBy(ptRequest.requestHeader.getCreatedBy());
        p.setCrDateTime(currentDateTime);

        Product old = productRepo.findProductById(p.getId());
        ProductHis productHis = modelMapper.map(old, ProductHis.class);
        productHis.setUptDateTime(currentDateTime);
        productHis.setUpdatedBy(ptRequest.requestHeader.getCreatedBy());
        productHis.setId(null);

        productHisRepo.save(productHis);
        Product product = productRepo.save(p);

        ptResponse.setBody(modelMapper.map(product, ProductDto.class));
        HeaderMapper.success(ptResponse, "updated");
        return ptResponse;
    }

    @Transactional
    public ResponseHandler<ProductDto> deleteProductById(Long id) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();
        productRepo.deleteProductById(id);
        HeaderMapper.success(ptResponse, "deleted by id");
        return ptResponse;
    }

    public String CurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
