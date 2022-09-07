package com.example.mobitelproduct.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;
    private String pkgName;
    private String pkgRental;
    private String pkgTotalData;
    private String pkgAnyData;
    private String pkgNightData;
    private String pkg4GData;
    private String pkgValidity;
    private String createdBy;
    private String crDateTime;
}
