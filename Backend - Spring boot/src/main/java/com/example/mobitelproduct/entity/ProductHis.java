package com.example.mobitelproduct.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "updatehistory")
@DynamicUpdate
public class ProductHis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String pkgName;                 //Package Name

    @Column(nullable = false)
    private String pkgRental;               // Package Rental

    private String pkgTotalData;            // Package Total Data

    private String pkgAnyData;              // Package Anytime Data Bundle

    private String pkgNightData;            // Package Night time Data Bundle

    private String pkg4GData;               // Package Bonus 4G Data Bundle

    @Column(nullable = false)
    private String pkgValidity;             // Package Validity Time

    private String createdBy;               // The Person Package created

    private String crDateTime;              // Date and Time Package added to Product

    private String uptDateTime;             // Updated Date and Time

    private String updatedBy;               // The Person Package updated

}
