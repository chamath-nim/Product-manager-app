package com.example.mobitelproduct.repository;

import com.example.mobitelproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    Product findProductById(Long id);

    @Query("select count(p) from Product p")
    Long numOfRows();
}
