package com.healt_cost_prediction.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import com.healt_cost_prediction.enums.Quality;
import com.healt_cost_prediction.modal.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetailDTO {
private String id;
private String productId;
private String productName;
private String productCategory;
private String  manufacturer;
private String manufactureDate;
private String country;
private Double quantity;
private String unit;
private double price;
private String currency;
private String  timeStamp;
private Quality  quality;
public ProductDetailDTO(UUID id, Product product, String manufacturer,
    LocalDate manufactureDate, String country, double quantity, String unit, double price, String currency,
    Date timeStamp,Quality quality) {
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MMMM-yyy");
    SimpleDateFormat sdf=new SimpleDateFormat("dddd-MMMM-yyy");
    this.id = id.toString();
    this.productName = product.getName();
    this.productCategory = product.getCategory();
    this.manufacturer = manufacturer;
    this.manufactureDate =formatter.format(manufactureDate);
    this.country = country;
    this.quantity = quantity;
    this.unit = unit;
    this.price = price;
    this.currency = currency;
    this.timeStamp = sdf.format(timeStamp);
    this.quality=quality;
}
}
