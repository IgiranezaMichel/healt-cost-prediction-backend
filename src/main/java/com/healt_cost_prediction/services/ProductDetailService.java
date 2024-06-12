package com.healt_cost_prediction.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.healt_cost_prediction.Mapper.ProductDetailMapper;
import com.healt_cost_prediction.dto.ProductDetailDTO;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.modal.Product;
import com.healt_cost_prediction.modal.ProductDetail;
import com.healt_cost_prediction.repository.ProductDetailRepository;
import com.healt_cost_prediction.repository.ProductRepository;

@Service
public class ProductDetailService {
@Autowired private ProductDetailRepository productDetailRepository;
@Autowired private ProductRepository productRepository;
private ProductDetailMapper productDetailMapper=new ProductDetailMapper();
public ResponseEntity<String> create(ProductDetailDTO productDetailDTO) {
  if(productDetailDTO.getProductId().length()==0)return new ResponseEntity<>("Product  is required",HttpStatus.BAD_REQUEST);
   else if(productDetailDTO.getManufacturer().length()==0)return new ResponseEntity<>("Product Manufacturer is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getManufactureDate().length()==0)return new ResponseEntity<>("Manufacture date is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getCurrency().length()==0)return new ResponseEntity<>("Currency is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getCountry().length()==0)return new ResponseEntity<>("Country of Manufactured product is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getPrice()==0)return new ResponseEntity<>("Product price is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getQuantity().isNaN()||productDetailDTO.getQuantity()==null||productDetailDTO.getQuantity()==0)return new ResponseEntity<>("Product Quantity is required",HttpStatus.BAD_REQUEST);
       else{
    Product product=productRepository.findById(UUID.fromString(productDetailDTO.getProductId())).orElseThrow();
      ProductDetail productDetail=productDetailRepository.save(new ProductDetail(product, productDetailDTO.getManufacturer(), LocalDate.parse(productDetailDTO.getManufactureDate()), productDetailDTO.getCountry(), productDetailDTO.getQuantity(), productDetailDTO.getUnit(), productDetailDTO.getPrice(), productDetailDTO.getCurrency(),new Date(), productDetailDTO.getQuality()));
      return new ResponseEntity<>(productDetail.getProduct().getName()+" saved successful",HttpStatus.CREATED);
   }
}

public ResponseEntity<String> update(ProductDetailDTO productDetailDTO) {
    if(productDetailDTO.getProductId().length()==0)return new ResponseEntity<>("Product  is required",HttpStatus.BAD_REQUEST);
    if(productDetailDTO.getId().length()==0)return new ResponseEntity<>("Product  is required",HttpStatus.BAD_REQUEST);
    else if(productDetailDTO.getManufacturer().length()==0)return new ResponseEntity<>("Product Manufacturer is required",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getManufactureDate().length()==0)return new ResponseEntity<>("Manufacture date is required",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getCurrency().length()==0)return new ResponseEntity<>("Currency is required",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getCountry().length()==0)return new ResponseEntity<>("Country of Manufactured product is required",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getPrice()==0)return new ResponseEntity<>("Product price is required",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getQuantity().isNaN())return new ResponseEntity<>("Add valid quantity",HttpStatus.BAD_REQUEST);
     else if(productDetailDTO.getQuantity()==null)return new ResponseEntity<>("Product Quantity quantity",HttpStatus.BAD_REQUEST);
    else{
     Product product=productRepository.findById(UUID.fromString(productDetailDTO.getProductId())).orElseThrow();
       ProductDetail productDetail=productDetailRepository.save(new ProductDetail(product, productDetailDTO.getManufacturer(), LocalDate.parse(productDetailDTO.getManufactureDate()), productDetailDTO.getCountry(), productDetailDTO.getQuantity(), productDetailDTO.getUnit(), productDetailDTO.getPrice(), productDetailDTO.getCurrency(),new Date(), productDetailDTO.getQuality()));
       return new ResponseEntity<>(productDetail.getProduct().getName()+" saved successful",HttpStatus.CREATED);
    }
}

public Pagination<ProductDetailDTO> productList(String search, int pageNumber, int pageSize) {
  if(search.length()==0){
        Page<ProductDetail> page = productDetailRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(productDetailMapper).toList(), page.getNumber(), page.getTotalPages());
    }else{
        Page<ProductDetail> page = productDetailRepository.findAllByProductNameContainingIgnoreCase(search,PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(productDetailMapper).toList(), page.getNumber(), page.getTotalPages());
    }
}

public ResponseEntity<String> deleteProductDetail(String productDetailId) {
  try {
    ProductDetail product=productDetailRepository.findById(UUID.fromString(productDetailId)).orElseThrow();
    productDetailRepository.delete(product);
    return new ResponseEntity<>("Item deleted successful",HttpStatus.OK);
   } catch (Exception e) {
    return new ResponseEntity<>("Product not found",HttpStatus.BAD_REQUEST);
   }
}

public List<ProductDetailDTO> allProductList() {
 return productDetailRepository.findAll().stream().map(productDetailMapper).toList();
}
}
