package com.healt_cost_prediction.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healt_cost_prediction.dto.BarchartDTO;
import com.healt_cost_prediction.modal.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT AVG(pd.price),extract(year from pd.manufactureDate) FROM  Product p,ProductDetail pd where p=:product\n" + //
                "group by extract(year from pd.manufactureDate) order by extract(year from pd.manufactureDate) asc")
    List<Object> productPriceStatistics(Product product);
    @Query("SELECT AVG(pd.price),extract(year from pd.manufactureDate) FROM  Product p,ProductDetail pd where p=pd.product\n" + //
                "group by extract(year from pd.manufactureDate) order by extract(year from pd.manufactureDate) asc")
    List<Object> getAllproductPriceStatistic();
    @Query("SELECT new com.healt_cost_prediction.dto.BarchartDTO(p.name,AVG(pd.price),extract(year from pd.manufactureDate))  FROM  Product p,ProductDetail pd where p=pd.product\n" + //
                "group by extract(year from pd.manufactureDate),p.name order by extract(year from pd.manufactureDate) asc")
    List<BarchartDTO> getAllProductPriceStatistics();
    Page<Product> findAllByNameContainingIgnoreCase(String search, Pageable of);
}

