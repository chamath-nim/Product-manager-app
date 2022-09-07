package com.example.mobitelproduct.repository;

import com.example.mobitelproduct.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepo {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilters(Long pageSize, Long pageNumber, Product product){

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> employeeRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(product, employeeRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) (pageNumber * pageSize));
        typedQuery.setMaxResults(Math.toIntExact(pageSize));

        Pageable paging = PageRequest.of(Math.toIntExact(pageNumber), Math.toIntExact(pageSize));
        long productCount = getProductsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), paging, productCount);
    }

    private Predicate getPredicate(Product product, Root<Product> employeeRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(product.getPkgName())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgName"), product.getPkgName()));
        }
        if (product.getPkgRental() != 0){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgRental"), product.getPkgRental()));
        }
        if (Objects.nonNull(product.getPkgTotalData())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgTotalData"), product.getPkgTotalData()));
        }
        if (Objects.nonNull(product.getPkgAnyData())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgAnyData"), product.getPkgAnyData()));
        }
        if (Objects.nonNull(product.getPkgNightData())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgNightData"), product.getPkgNightData()));
        }
        if (Objects.nonNull(product.getPkg4GData())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkg4GData"), product.getPkg4GData()));
        }
        if (Objects.nonNull(product.getPkgValidity())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("pkgValidity"), product.getPkgValidity()));
        }
        if (Objects.nonNull(product.getCreatedBy())){
            predicates.add(criteriaBuilder.equal(employeeRoot.get("createdBy"), product.getCreatedBy()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private long getProductsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
