package com.healt_cost_prediction.modal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.healt_cost_prediction.enums.Quality;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetail {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@ManyToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
private Product product;
private String  manufacturer;
private LocalDate manufactureDate;
private String country;
private double quantity;
private String unit;
private double price;
private String currency;
private Date  timeStamp;
@Enumerated(EnumType.STRING)
private Quality quality;
public ProductDetail(Product product, String manufacturer, LocalDate manufactureDate, String country, double quantity,
        String unit, double price, String currency, Date timeStamp, Quality quality) {
    this.product = product;
    this.manufacturer = manufacturer;
    this.manufactureDate = manufactureDate;
    this.country = country;
    this.quantity = quantity;
    this.unit = unit;
    this.price = price;
    this.currency = currency;
    this.timeStamp = timeStamp;
    this.quality = quality;
}
}
