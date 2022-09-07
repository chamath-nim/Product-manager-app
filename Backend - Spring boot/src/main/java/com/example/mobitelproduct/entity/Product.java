package com.example.mobitelproduct.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "products_test1")
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "PKG_NAMES", nullable = false)
    private String pkgName;         //Package Name

    @Column(name = "PKG_RENTAL", nullable = false)
    private Long pkgRental;       // Package Rental

    @Column(name = "PKG_TOTAL_DATA")
    private String pkgTotalData;    // Package Total Data

    @Column(name = "PKG_ANY_DATA")
    private String pkgAnyData;      // Package Anytime Data Bundle

    @Column(name = "PKG_NIGHT_DATA")
    private String pkgNightData;    // Package Night time Data Bundle

    @Column(name = "PKG_4G_DATA")
    private String pkg4GData;       // Package Bonus 4G Data Bundle

    @Column(nullable = false, name = "PKG_VALIDITY")
    private String pkgValidity;     // Package Validity Time

    @Column(name = "CREATED_BY")
    private String createdBy;       // The Person Package Requested

    @Column(name = "PKG_CR_DATETIME")
    private String crDateTime;        // Transaction Date and Time
}
