package com.example.mobitelproduct.dto;

import lombok.Data;

@Data
public class ProductHisDto {

    private Long id;
    private String pkgName;                 //Package Name
    private String pkgRental;               // Package Rental
    private String pkgTotalData;            // Package Total Data
    private String pkgAnyData;              // Package Anytime Data Bundle
    private String pkgNightData;            // Package Night time Data Bundle
    private String pkg4GData;              // Package Bonus 4G Data Bundle
    private String pkgValidity;             // Package Validity Time
    private String createdBy;               // The Person Package created
    private String crDateTime;              // Date and Time Package added to Product
    private String uptDateTime;             // Updated Date and Time
    private String updatedBy;               // The Person Package updated

}
