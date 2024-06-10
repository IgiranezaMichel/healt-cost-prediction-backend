package com.healt_cost_prediction.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healt_cost_prediction.modal.Product;
import com.healt_cost_prediction.modal.ProductDetail;
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, UUID>{
    Page<ProductDetail> findAllByProductNameContainingIgnoreCase(String search, PageRequest of);
    List<ProductDetail> findByProductName(String productName);
    List<ProductDetail> findByProduct(Product product);
    List<ProductDetail> findByProductAndManufactureDateAfterAndManufactureDateBefore(Product productRes,
            LocalDate fromDate, LocalDate toDate);

}
