package com.healt_cost_prediction.services;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.healt_cost_prediction.Mapper.ProductMapper;
import com.healt_cost_prediction.dto.BarchartDTO;
import com.healt_cost_prediction.dto.ProductDTO;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.modal.Product;
import com.healt_cost_prediction.repository.ProductRepository;
@Service
public class ProductService {
@Autowired private ProductRepository productRepository;
 private ProductMapper productMapper=new ProductMapper();
public ResponseEntity<String> create(ProductDTO productDTO) {
  if(productDTO.getName().length()==0)return new ResponseEntity<>("Product name is required",HttpStatus.BAD_REQUEST);
  else if(productDTO.getCategory().length()==0)return new ResponseEntity<>("Product category is required",HttpStatus.BAD_REQUEST);
 else{
    Product product=productRepository.save(new Product(productDTO.getName(), productDTO.getCategory()));
    return new ResponseEntity<>(product.getName()+" saved successful",HttpStatus.CREATED);
 }
}

public ResponseEntity<String> update(ProductDTO productDTO) {
    if(productDTO.getId().length()==0)return new ResponseEntity<>("Product is required",HttpStatus.BAD_REQUEST);
    if(productDTO.getName().length()==0)return new ResponseEntity<>("Product name is required",HttpStatus.BAD_REQUEST);
    else if(productDTO.getCategory().length()==0)return new ResponseEntity<>("Product category is required",HttpStatus.BAD_REQUEST);
   else{
      Product product=productRepository.save(new Product(UUID.fromString(productDTO.getId()),productDTO.getName(), productDTO.getCategory()));
      return new ResponseEntity<>(product.getName()+" saved successful",HttpStatus.CREATED);
   }
}

public Pagination<ProductDTO> productList(String search, int pageNumber, int pageSize) {
    if(search.length()==0){
        Page<Product> page = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(productMapper).toList(), page.getNumber(), page.getTotalPages());
    }else{
        Page<Product> page = productRepository.findAllByNameContainingIgnoreCase(search,PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(productMapper).toList(), page.getNumber(), page.getTotalPages());
    }
}

public ResponseEntity<String> deleteProduct(String productId) {
   try {
    Product product=productRepository.findById(UUID.fromString(productId)).orElseThrow();
    productRepository.delete(product);
    return new ResponseEntity<>(product.getName()+" deleted successful",HttpStatus.OK);
   } catch (Exception e) {
    return new ResponseEntity<>("Product not found",HttpStatus.BAD_REQUEST);
   }
}
public List<ProductDTO> getAllProductList() {
 return productRepository.findAll().stream().map(productMapper).toList();
}
public List<Object> getProductPriceStatistics(String productId){
    Product product=productRepository.findById(UUID.fromString(productId)).orElse(null);
   return productRepository.productPriceStatistics(product);
}

public ProductDTO getProductDetail(String productId) {
    Product product=productRepository.findById(UUID.fromString(productId)).orElse(null);
    return new ProductDTO(product.getName(), product.getCategory());
}
public List<BarchartDTO> getAllProductPriceStatistics(){
    return productRepository.getAllProductPriceStatistics();
}
}
