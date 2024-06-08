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

import com.healt_cost_prediction.dto.ProductDetailDTO;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.services.ProductDetailService;
@RestController()
@RequestMapping("/api/productDetail")
public class ProductDetailController {
@Autowired private ProductDetailService productService;
@PostMapping(value="create")
public ResponseEntity<String>createProductDetail(@RequestBody ProductDetailDTO productDetailDTO){
    return productService.create(productDetailDTO);
}
@PostMapping(value="update")
public ResponseEntity<String>updateProductDetail(@RequestBody ProductDetailDTO productDTO){
    return productService.update(productDTO);
}
@GetMapping(value="all")
public Pagination<ProductDetailDTO>productDetailList(@RequestParam(name="search")String search,@RequestParam(defaultValue = "0")int pageNumber,@RequestParam(defaultValue = "10")int pageSize){
    return productService.productList(search,pageNumber,pageSize);
}
@GetMapping(value="get/all")
public List<ProductDetailDTO>allProductDetailList(){
    return productService.allProductList();
}
@DeleteMapping(value="delete")
public ResponseEntity<String>deleteProductDetail(@RequestParam()String productDetailId){
    return productService.deleteProductDetail(productDetailId);
}
}
