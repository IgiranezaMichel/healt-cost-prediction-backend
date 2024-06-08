package com.healt_cost_prediction.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healt_cost_prediction.dto.BarchartDTO;
import com.healt_cost_prediction.dto.ProductDTO;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.services.ProductService;
@RestController
@RequestMapping("api/product")
public class ProductController {
@Autowired private ProductService productService;
@PostMapping(value="create")
public ResponseEntity<String>createProduct(@RequestBody ProductDTO productDTO){
    return productService.create(productDTO);
}
@PostMapping(value="update")
public ResponseEntity<String>updateProduct(@RequestBody ProductDTO productDTO){
    return productService.update(productDTO);
}
@GetMapping(value="all")
public Pagination<ProductDTO>productList(@RequestParam()String search,@RequestParam(defaultValue = "0")int pageNumber,@RequestParam(defaultValue = "10")int pageSize){
    return productService.productList(search,pageNumber,pageSize);
}
@DeleteMapping(value="delete")
public ResponseEntity<String>deleteProduct(@RequestParam()String productId){
    return productService.deleteProduct(productId);
}
@GetMapping(value="get/all")
public List<ProductDTO>allProductDetailList(){
    return productService.getAllProductList();
}
@GetMapping()
public ProductDTO productDetail(@RequestParam String productId){
    return productService.getProductDetail(productId);
}
@GetMapping(value = "statistic/all")
public List<BarchartDTO> getAllProductPriceStatistics(){
    return productService.getAllProductPriceStatistics();
}

@GetMapping(value="statistics")
public List<Object> getProductPriceStatistics(@RequestParam(name = "productId")String productId){
    return productService.getProductPriceStatistics(productId);
}
}
