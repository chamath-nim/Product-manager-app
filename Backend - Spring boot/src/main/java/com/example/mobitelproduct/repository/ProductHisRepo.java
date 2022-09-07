package com.example.mobitelproduct.repository;

import com.example.mobitelproduct.entity.ProductHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductHisRepo extends JpaRepository<ProductHis, Long> {
    @Query("select count(p) from ProductHis p")
    Long numOfRows();

}
